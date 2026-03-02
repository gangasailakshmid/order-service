package com.oms.order_service.service;

import com.oms.order_service.dto.StateTaxResponse;
import com.oms.order_service.entity.StateTaxEntity;
import com.oms.order_service.exception.ResourceNotFoundException;
import com.oms.order_service.repository.StateTaxRepository;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class StateTaxService {

	private final StateTaxRepository stateTaxRepository;

	@Transactional(readOnly = true)
	public List<StateTaxResponse> getAll() {
		return stateTaxRepository.findAll().stream().map(this::map).toList();
	}

	@Transactional(readOnly = true)
	public StateTaxResponse getByStateCode(String stateCode) {
		return map(findByCode(stateCode));
	}

	private StateTaxEntity findByCode(String stateCode) {
		return stateTaxRepository.findByStateCodeIgnoreCase(stateCode)
				.orElseThrow(() -> new ResourceNotFoundException("State tax not found for stateCode: " + stateCode));
	}

	private StateTaxResponse map(StateTaxEntity entity) {
		return new StateTaxResponse(
				entity.getId(),
				entity.getStateCode(),
				entity.getStateName(),
				entity.getTaxPercentage(),
				entity.getActive()
		);
	}

	@Transactional
	public void seedDefaultsIfEmpty() {
		if (stateTaxRepository.count() > 0) {
			return;
		}
		stateTaxRepository.saveAll(List.of(
				tax("CA", "California", 8.25),
				tax("NY", "New York", 8.88),
				tax("TX", "Texas", 6.25),
				tax("FL", "Florida", 6.00),
				tax("WA", "Washington", 6.50),
				tax("NJ", "New Jersey", 6.63),
				tax("IL", "Illinois", 6.25),
				tax("GA", "Georgia", 4.00),
				tax("NC", "North Carolina", 4.75),
				tax("AZ", "Arizona", 5.60)
		));
	}

	private StateTaxEntity tax(String code, String name, double taxPercentage) {
		StateTaxEntity entity = new StateTaxEntity();
		entity.setStateCode(code);
		entity.setStateName(name);
		entity.setTaxPercentage(BigDecimal.valueOf(taxPercentage));
		entity.setActive(true);
		return entity;
	}
}
