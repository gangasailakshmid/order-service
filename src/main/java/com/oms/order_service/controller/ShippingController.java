package com.oms.order_service.controller;

import com.oms.order_service.dto.ShippingRequest;
import com.oms.order_service.dto.ShippingResponse;
import com.oms.order_service.service.ShippingService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/shipping")
@RequiredArgsConstructor
public class ShippingController {

	private final ShippingService shippingService;

	@GetMapping
	public List<ShippingResponse> getAll() {
		return shippingService.getAll();
	}

	@GetMapping("/{orderNumber}")
	public ShippingResponse getByOrderNumber(@PathVariable String orderNumber) {
		return shippingService.getByOrderNumber(orderNumber);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ShippingResponse create(@Valid @RequestBody ShippingRequest request) {
		return shippingService.create(request);
	}

	@PutMapping("/{orderNumber}")
	public ShippingResponse update(@PathVariable String orderNumber, @Valid @RequestBody ShippingRequest request) {
		return shippingService.update(orderNumber, request);
	}

	@DeleteMapping("/{orderNumber}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String orderNumber) {
		shippingService.delete(orderNumber);
	}
}
