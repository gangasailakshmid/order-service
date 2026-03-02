package com.oms.order_service.dto;

import com.oms.order_service.entity.ShippingStatus;
import java.time.LocalDate;

public record ShippingResponse(
		Long id,
		String orderNumber,
		String addressLine1,
		String addressLine2,
		String city,
		String state,
		String postalCode,
		String country,
		ShippingStatus status,
		LocalDate shippedDate,
		LocalDate deliveryDate
) {
}
