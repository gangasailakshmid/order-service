package com.oms.order_service.repository;

import com.oms.order_service.entity.OrderEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
	Optional<OrderEntity> findByOrderNumber(String orderNumber);
}
