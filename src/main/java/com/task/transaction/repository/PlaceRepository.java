package com.task.transaction.repository;

import com.task.transaction.domain.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {

    Boolean existsByName(String name);

    Optional<Place> findByName(String name);

    List<Place> findAllByRegionId(Long regionId);
}
