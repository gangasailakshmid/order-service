package com.oms.order_service.repository;

import com.oms.order_service.entity.CouponEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
	Optional<CouponEntity> findByCouponCodeIgnoreCase(String couponCode);
}
