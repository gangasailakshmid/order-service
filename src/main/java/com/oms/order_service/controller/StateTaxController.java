package com.oms.order_service.controller;

import com.oms.order_service.dto.StateTaxResponse;
import com.oms.order_service.service.StateTaxService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/taxes")
@RequiredArgsConstructor
public class StateTaxController {

	private final StateTaxService stateTaxService;

	@GetMapping
	public List<StateTaxResponse> getAll() {
		return stateTaxService.getAll();
	}

	@GetMapping("/{stateCode}")
	public StateTaxResponse getByStateCode(@PathVariable String stateCode) {
		return stateTaxService.getByStateCode(stateCode);
	}
}
