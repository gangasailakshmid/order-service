package com.oms.order_service.service;

import com.oms.order_service.dto.CouponResponse;
import com.oms.order_service.dto.CouponValidationResponse;
import com.oms.order_service.entity.CouponEntity;
import com.oms.order_service.exception.ResourceNotFoundException;
import com.oms.order_service.repository.CouponRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CouponService {

	private final CouponRepository couponRepository;

	@Transactional(readOnly = true)
	public List<CouponResponse> getAll() {
		return couponRepository.findAll().stream().map(this::map).toList();
	}

	@Transactional(readOnly = true)
	public CouponResponse getByCode(String couponCode) {
		return map(findByCode(couponCode));
	}

	@Transactional(readOnly = true)
	public CouponValidationResponse validate(String couponCode) {
		CouponEntity coupon = findByCode(couponCode);
		LocalDate today = LocalDate.now();

		if (!Boolean.TRUE.equals(coupon.getActive())) {
			return new CouponValidationResponse(coupon.getCouponCode(), coupon.getDiscountPercentage(),
					coupon.getCombinable(), false, "Coupon is inactive.");
		}
		if (coupon.getValidFrom() != null && today.isBefore(coupon.getValidFrom())) {
			return new CouponValidationResponse(coupon.getCouponCode(), coupon.getDiscountPercentage(),
					coupon.getCombinable(), false, "Coupon is not active yet.");
		}
		if (coupon.getValidUntil() != null && today.isAfter(coupon.getValidUntil())) {
			return new CouponValidationResponse(coupon.getCouponCode(), coupon.getDiscountPercentage(),
					coupon.getCombinable(), false, "Coupon has expired.");
		}
		return new CouponValidationResponse(coupon.getCouponCode(), coupon.getDiscountPercentage(),
				coupon.getCombinable(), true, "Coupon is valid.");
	}

	private CouponEntity findByCode(String couponCode) {
		return couponRepository.findByCouponCodeIgnoreCase(couponCode)
				.orElseThrow(() -> new ResourceNotFoundException("Coupon not found: " + couponCode));
	}

	private CouponResponse map(CouponEntity coupon) {
		return new CouponResponse(
				coupon.getId(),
				coupon.getCouponCode(),
				coupon.getDiscountPercentage(),
				coupon.getActive(),
				coupon.getCombinable(),
				coupon.getValidFrom(),
				coupon.getValidUntil(),
				coupon.getDescription()
		);
	}

	@Transactional
	public void seedDefaultsIfEmpty() {
		if (couponRepository.count() == 0) {
			couponRepository.saveAll(List.of(
					coupon("SAVE10", "10% off", BigDecimal.valueOf(10), true, true, LocalDate.now().minusDays(2), LocalDate.now().plusMonths(6)),
					coupon("SAVE20", "20% off", BigDecimal.valueOf(20), true, false, LocalDate.now().minusDays(2), LocalDate.now().plusMonths(3)),
					coupon("FEST30", "30% off festival sale", BigDecimal.valueOf(30), true, false, LocalDate.now().minusDays(2), LocalDate.now().plusMonths(2)),
					coupon("WELCOME15", "15% off for new users", BigDecimal.valueOf(15), true, true, LocalDate.now().minusDays(2), LocalDate.now().plusMonths(12))
			));
			return;
		}
		syncCombinability("SAVE10", true);
		syncCombinability("SAVE20", false);
		syncCombinability("FEST30", false);
		syncCombinability("WELCOME15", true);
	}

	private void syncCombinability(String code, boolean combinable) {
		couponRepository.findByCouponCodeIgnoreCase(code).ifPresent(coupon -> {
			coupon.setCombinable(combinable);
			couponRepository.save(coupon);
		});
	}

	private CouponEntity coupon(String code, String description, BigDecimal discount, boolean active,
			boolean combinable, LocalDate validFrom, LocalDate validUntil) {
		CouponEntity entity = new CouponEntity();
		entity.setCouponCode(code);
		entity.setDescription(description);
		entity.setDiscountPercentage(discount);
		entity.setActive(active);
		entity.setCombinable(combinable);
		entity.setValidFrom(validFrom);
		entity.setValidUntil(validUntil);
		return entity;
	}
}
