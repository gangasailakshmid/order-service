package com.oms.order_service.dto;

import com.oms.order_service.entity.ShippingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ShippingRequest(
		@NotBlank String orderNumber,
		@NotBlank String addressLine1,
		String addressLine2,
		@NotBlank String city,
		@NotBlank String state,
		@NotBlank String postalCode,
		@NotBlank String country,
		@NotNull ShippingStatus status,
		LocalDate shippedDate,
		LocalDate deliveryDate
) {
}
