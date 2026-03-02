package com.oms.order_service.repository;

import com.oms.order_service.entity.StateTaxEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateTaxRepository extends JpaRepository<StateTaxEntity, Long> {
	Optional<StateTaxEntity> findByStateCodeIgnoreCase(String stateCode);
}
