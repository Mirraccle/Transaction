package com.task.transaction.service.impl;

import com.task.transaction.domain.Region;
import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.PlaceDto;
import com.task.transaction.dto.RegionDto;
import com.task.transaction.dto.enums.ResponseStatus;
import com.task.transaction.repository.RegionRepository;
import com.task.transaction.service.PlaceService;
import com.task.transaction.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    private final PlaceService placeService;

    public RegionServiceImpl(RegionRepository regionRepository, PlaceService placeService) {
        this.regionRepository = regionRepository;
        this.placeService = placeService;
    }

    @Override
    public CommonResponse createRegion(RegionDto regionDto) {
        Optional<Region> optionalRegion = regionRepository.findRegionByName(regionDto.getRegionName());
        if (optionalRegion.isPresent()) {
            return new CommonResponse(ResponseStatus.FAILED, "Region with this name is already exists");
        }
        Region region = new Region();
        region.setName(regionDto.getRegionName());
        Region savedRegion = regionRepository.save(region);
        List<PlaceDto> places = placeService.createPlaces(regionDto, savedRegion.getId());
        return new CommonResponse(ResponseStatus.SUCCESS, places);
    }
}
