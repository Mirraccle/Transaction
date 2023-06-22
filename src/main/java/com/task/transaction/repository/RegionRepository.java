package com.task.transaction.repository;

import com.task.transaction.domain.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
    Optional<Region> findRegionByName(String name);

    List<Region> findRegionsByCarriersId(Long carrierId);
}
