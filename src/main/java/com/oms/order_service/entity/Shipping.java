package com.oms.order_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "shipping", indexes = {
		@Index(name = "idx_shipping_status", columnList = "status"),
		@Index(name = "idx_shipping_postal_code", columnList = "postal_code")
})
public class Shipping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "order_id", nullable = false, unique = true)
	private OrderEntity order;

	@Column(name = "address_line1", nullable = false, length = 200)
	private String addressLine1;

	@Column(name = "address_line2", length = 200)
	private String addressLine2;

	@Column(nullable = false, length = 80)
	private String city;

	@Column(nullable = false, length = 80)
	private String state;

	@Column(name = "postal_code", nullable = false, length = 20)
	private String postalCode;

	@Column(nullable = false, length = 60)
	private String country;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private ShippingStatus status;

	@Column(name = "shipped_date")
	private LocalDate shippedDate;

	@Column(name = "delivery_date")
	private LocalDate deliveryDate;
}
