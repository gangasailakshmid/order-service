package com.oms.order_service.repository;

import com.oms.order_service.entity.Shipping;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {
	Optional<Shipping> findByOrderId(Long orderId);

	Optional<Shipping> findByOrderOrderNumber(String orderNumber);
}
