package com.oms.order_service.dto;

import com.oms.order_service.entity.OrderStatus;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderResponse(
		Long id,
		String orderNumber,
		String customerCode,
		String productId,
		String productCode,
		Integer quantity,
		BigDecimal unitPrice,
		OrderStatus status,
		LocalDateTime orderDate
) {
}
