package com.oms.order_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "state_tax")
public class StateTaxEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "state_code", nullable = false, unique = true, length = 10)
	private String stateCode;

	@Column(name = "state_name", nullable = false, length = 80)
	private String stateName;

	@Column(name = "tax_percentage", nullable = false, precision = 5, scale = 2)
	private BigDecimal taxPercentage;

	@Column(nullable = false)
	private Boolean active = Boolean.TRUE;
}
