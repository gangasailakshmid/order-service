package com.oms.order_service.controller;

import com.oms.order_service.dto.OrderRequest;
import com.oms.order_service.dto.OrderResponse;
import com.oms.order_service.service.OrderService;
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
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping
	public List<OrderResponse> getAll() {
		return orderService.getAll();
	}

	@GetMapping("/{orderNumber}")
	public OrderResponse getByOrderNumber(@PathVariable String orderNumber) {
		return orderService.getByOrderNumber(orderNumber);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OrderResponse create(@Valid @RequestBody OrderRequest request) {
		return orderService.create(request);
	}

	@PutMapping("/{orderNumber}")
	public OrderResponse update(@PathVariable String orderNumber, @Valid @RequestBody OrderRequest request) {
		return orderService.update(orderNumber, request);
	}

	@DeleteMapping("/{orderNumber}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable String orderNumber) {
		orderService.delete(orderNumber);
	}
}
