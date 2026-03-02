package com.oms.order_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponDataInitializer {

	private final CouponService couponService;

	@EventListener(ApplicationReadyEvent.class)
	public void seedCoupons() {
		couponService.seedDefaultsIfEmpty();
	}
}
