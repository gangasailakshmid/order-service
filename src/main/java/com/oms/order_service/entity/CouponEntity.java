package com.oms.order_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "coupon")
public class CouponEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "coupon_code", nullable = false, unique = true, length = 40)
	private String couponCode;

	@Column(name = "discount_percentage", nullable = false, precision = 5, scale = 2)
	private BigDecimal discountPercentage;

	@Column(nullable = false)
	private Boolean active = Boolean.TRUE;

	@Column(nullable = false)
	private Boolean combinable = Boolean.FALSE;

	@Column(name = "valid_from")
	private LocalDate validFrom;

	@Column(name = "valid_until")
	private LocalDate validUntil;

	@Column(length = 200)
	private String description;
}
