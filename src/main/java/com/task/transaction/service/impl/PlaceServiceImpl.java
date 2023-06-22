package com.task.transaction.service.impl;

import com.task.transaction.domain.Place;
import com.task.transaction.dto.PlaceDto;
import com.task.transaction.dto.RegionDto;
import com.task.transaction.repository.PlaceRepository;
import com.task.transaction.repository.RegionRepository;
import com.task.transaction.service.PlaceService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceServiceImpl(PlaceRepository placeRepository) {
        this.placeRepository = placeRepository;
    }

    @Override
    public List<PlaceDto> createPlaces(RegionDto regionDto, Long regionId) {
        regionDto.getPlaces().forEach(placeDto -> {
            if (!placeRepository.existsByName(placeDto.getPlaceName())) {
                Place place = new Place();
                place.setRegionId(regionId);
                place.setName(placeDto.getPlaceName());
                placeRepository.save(place);
            }
        });
        return regionDto.getPlaces()
                .stream()
                .sorted(Comparator.comparing(PlaceDto::getPlaceName))
                .collect(Collectors.toList());
    }
}
