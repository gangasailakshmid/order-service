package com.oms.order_service.service;

import com.oms.order_service.dto.OrderRequest;
import com.oms.order_service.dto.OrderResponse;
import com.oms.order_service.entity.OrderEntity;
import com.oms.order_service.exception.ResourceNotFoundException;
import com.oms.order_service.repository.OrderRepository;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	@Transactional(readOnly = true)
	public List<OrderResponse> getAll() {
		return orderRepository.findAll().stream().map(this::map).toList();
	}

	@Transactional(readOnly = true)
	public OrderResponse getByOrderNumber(String orderNumber) {
		return map(findByOrderNumberEntity(orderNumber));
	}

	@Transactional
	public OrderResponse create(OrderRequest request) {
		OrderEntity order = new OrderEntity();
		apply(request, order);
		order.setOrderDate(LocalDateTime.now());
		return map(orderRepository.save(order));
	}

	@Transactional
	public OrderResponse update(String orderNumber, OrderRequest request) {
		OrderEntity order = findByOrderNumberEntity(orderNumber);
		apply(request, order);
		return map(orderRepository.save(order));
	}

	@Transactional
	public void delete(String orderNumber) {
		orderRepository.delete(findByOrderNumberEntity(orderNumber));
	}

	public OrderEntity findByOrderNumberEntity(String orderNumber) {
		return orderRepository.findByOrderNumber(orderNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Order not found: " + orderNumber));
	}

	private void apply(OrderRequest request, OrderEntity order) {
		order.setOrderNumber(request.orderNumber());
		order.setCustomerCode(request.customerCode());
		order.setProfileId(request.profileId());
		order.setProductId(request.productId());
		order.setProductCode(request.productCode());
		order.setQuantity(request.quantity());
		order.setUnitPrice(request.unitPrice());
		order.setStatus(request.status());
	}

	private OrderResponse map(OrderEntity order) {
		return new OrderResponse(
				order.getId(),
				order.getOrderNumber(),
				order.getCustomerCode(),
				order.getProfileId(),
				order.getProductId(),
				order.getProductCode(),
				order.getQuantity(),
				order.getUnitPrice(),
				order.getStatus(),
				order.getOrderDate()
		);
	}
}
