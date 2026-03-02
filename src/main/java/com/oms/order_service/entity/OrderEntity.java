package com.oms.order_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders", indexes = {
		@Index(name = "idx_orders_customer_date", columnList = "customer_code,order_date"),
		@Index(name = "idx_orders_product_status", columnList = "product_code,status")
})
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "order_number", nullable = false, unique = true, length = 40)
	private String orderNumber;

	@Column(name = "customer_code", nullable = false, length = 40)
	private String customerCode;

	@Column(name = "product_code", nullable = false, length = 40)
	private String productCode;

	@Column(nullable = false)
	private Integer quantity;

	@Column(name = "unit_price", nullable = false, precision = 12, scale = 2)
	private BigDecimal unitPrice;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private OrderStatus status;

	@Column(name = "order_date", nullable = false)
	private LocalDateTime orderDate;

	@OneToOne(mappedBy = "order")
	private Shipping shipping;
}
