package com.task.transaction.repository;

import com.task.transaction.domain.Carrier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarrierRepository extends JpaRepository<Carrier, Long> {

    List<Carrier> findCarriersByRegionsIdOrderByName(Long regionId);

    Boolean existsByName(String name);

    Optional<Carrier> findByName(String name);
}
