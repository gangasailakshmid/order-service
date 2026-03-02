package com.oms.order_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponSchemaPatchInitializer {

	private final JdbcTemplate jdbcTemplate;

	@EventListener(ApplicationReadyEvent.class)
	public void ensureCouponCombinableColumn() {
		jdbcTemplate.execute("ALTER TABLE coupon ADD COLUMN IF NOT EXISTS combinable BOOLEAN NOT NULL DEFAULT FALSE");
	}
}
