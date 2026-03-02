package com.oms.order_service.controller;

import com.oms.order_service.dto.CouponResponse;
import com.oms.order_service.dto.CouponValidationResponse;
import com.oms.order_service.service.CouponService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/coupons")
@RequiredArgsConstructor
public class CouponController {

	private final CouponService couponService;

	@GetMapping
	public List<CouponResponse> getAll() {
		return couponService.getAll();
	}

	@GetMapping("/{couponCode}")
	public CouponResponse getByCode(@PathVariable String couponCode) {
		return couponService.getByCode(couponCode);
	}

	@GetMapping("/{couponCode}/validate")
	public CouponValidationResponse validate(@PathVariable String couponCode) {
		return couponService.validate(couponCode);
	}
}
