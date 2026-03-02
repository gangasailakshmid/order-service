package com.oms.order_service.dto;

import java.math.BigDecimal;

public record StateTaxResponse(
		Long id,
		String stateCode,
		String stateName,
		BigDecimal taxPercentage,
		Boolean active
) {
}
