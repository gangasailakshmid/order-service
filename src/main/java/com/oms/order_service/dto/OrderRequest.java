package com.oms.order_service.dto;

import com.oms.order_service.entity.OrderStatus;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record OrderRequest(
		@NotBlank String orderNumber,
		@NotBlank String customerCode,
		@NotNull @Positive Long profileId,
		@NotNull @Positive Long productId,
		@NotBlank String productCode,
		@NotNull @Min(1) Integer quantity,
		@NotNull BigDecimal unitPrice,
		@NotNull OrderStatus status
) {
}
