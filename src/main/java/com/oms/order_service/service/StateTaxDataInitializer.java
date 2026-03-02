package com.oms.order_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StateTaxDataInitializer {

	private final StateTaxService stateTaxService;

	@EventListener(ApplicationReadyEvent.class)
	public void seedStateTaxes() {
		stateTaxService.seedDefaultsIfEmpty();
	}
}
