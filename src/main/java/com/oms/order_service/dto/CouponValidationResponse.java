package com.oms.order_service.dto;

import java.math.BigDecimal;

public record CouponValidationResponse(
		String couponCode,
		BigDecimal discountPercentage,
		Boolean combinable,
		boolean valid,
		String message
) {
}
