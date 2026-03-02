package com.oms.order_service.service;

import com.oms.order_service.dto.ShippingRequest;
import com.oms.order_service.dto.ShippingResponse;
import com.oms.order_service.entity.OrderEntity;
import com.oms.order_service.entity.Shipping;
import com.oms.order_service.exception.ResourceNotFoundException;
import com.oms.order_service.repository.ShippingRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShippingService {

	private final ShippingRepository shippingRepository;
	private final OrderService orderService;

	@Transactional(readOnly = true)
	public List<ShippingResponse> getAll() {
		return shippingRepository.findAll().stream().map(this::map).toList();
	}

	@Transactional(readOnly = true)
	public ShippingResponse getByOrderNumber(String orderNumber) {
		return map(findByOrderNumber(orderNumber));
	}

	@Transactional
	public ShippingResponse create(ShippingRequest request) {
		OrderEntity order = orderService.findByOrderNumberEntity(request.orderNumber());
		Shipping shipping = new Shipping();
		apply(request, shipping, order);
		return map(shippingRepository.save(shipping));
	}

	@Transactional
	public ShippingResponse update(String orderNumber, ShippingRequest request) {
		OrderEntity order = orderService.findByOrderNumberEntity(orderNumber);
		Shipping shipping = findByOrderNumber(orderNumber);
		apply(request, shipping, order);
		return map(shippingRepository.save(shipping));
	}

	@Transactional
	public void delete(String orderNumber) {
		shippingRepository.delete(findByOrderNumber(orderNumber));
	}

	private Shipping findByOrderNumber(String orderNumber) {
		return shippingRepository.findByOrderOrderNumber(orderNumber)
				.orElseThrow(() -> new ResourceNotFoundException("Shipping not found for orderNumber: " + orderNumber));
	}

	private void apply(ShippingRequest request, Shipping shipping, OrderEntity order) {
		shipping.setOrder(order);
		shipping.setAddressLine1(request.addressLine1());
		shipping.setAddressLine2(request.addressLine2());
		shipping.setCity(request.city());
		shipping.setState(request.state());
		shipping.setPostalCode(request.postalCode());
		shipping.setCountry(request.country());
		shipping.setStatus(request.status());
		shipping.setShippedDate(request.shippedDate());
		shipping.setDeliveryDate(request.deliveryDate());
	}

	private ShippingResponse map(Shipping shipping) {
		return new ShippingResponse(
				shipping.getId(),
				shipping.getOrder().getOrderNumber(),
				shipping.getAddressLine1(),
				shipping.getAddressLine2(),
				shipping.getCity(),
				shipping.getState(),
				shipping.getPostalCode(),
				shipping.getCountry(),
				shipping.getStatus(),
				shipping.getShippedDate(),
				shipping.getDeliveryDate()
		);
	}
}
