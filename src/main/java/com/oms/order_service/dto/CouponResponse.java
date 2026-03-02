package com.oms.order_service.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record CouponResponse(
		Long id,
		String couponCode,
		BigDecimal discountPercentage,
		Boolean active,
		Boolean combinable,
		LocalDate validFrom,
		LocalDate validUntil,
		String description
) {
}
