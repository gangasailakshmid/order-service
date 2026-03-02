package com.oms.order_service.dto;

import com.oms.order_service.entity.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record OrderRequest(
		@NotBlank String orderNumber,
		@NotBlank String customerCode,
		@NotBlank String productId,
		@NotNull @Min(1) Integer quantity,
		@NotNull BigDecimal unitPrice,
		@NotNull OrderStatus status
) {
}
