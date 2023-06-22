package com.task.transaction.service.impl;

import com.task.transaction.domain.Carrier;
import com.task.transaction.domain.Region;
import com.task.transaction.dto.CarrierDto;
import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.RegionNameDto;
import com.task.transaction.dto.enums.ResponseStatus;
import com.task.transaction.repository.CarrierRepository;
import com.task.transaction.repository.RegionRepository;
import com.task.transaction.service.CarrierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CarrierServiceImpl implements CarrierService {

    private final CarrierRepository carrierRepository;

    private final RegionRepository regionRepository;

    public CarrierServiceImpl(CarrierRepository carrierRepository, RegionRepository regionRepository) {
        this.carrierRepository = carrierRepository;
        this.regionRepository = regionRepository;
    }

    @Override
    public CommonResponse createCarrier(CarrierDto carrierDto) {

        if (carrierRepository.existsByName(carrierDto.getName())) {
            return new CommonResponse(ResponseStatus.FAILED, "Carrier with this name already exists");
        }

        Set<Region> regions = new HashSet<>();
        carrierDto.getRegions().forEach(regionNameDto -> {
            Optional<Region> optionalRegion = regionRepository.findRegionByName(regionNameDto.getRegionName());
            optionalRegion.ifPresent(regions::add);
        });

        Carrier carrier = new Carrier();
        carrier.setName(carrierDto.getName());
        carrier.setRegions(regions);
        carrierRepository.save(carrier);
        List<RegionNameDto> responseData = regions.stream()
                .map(region -> {
                    RegionNameDto regionNameDto = new RegionNameDto();
                    regionNameDto.setRegionName(region.getName());
                    return regionNameDto;
                })
                .sorted(Comparator.comparing(RegionNameDto::getRegionName))
                .collect(Collectors.toList());
        return new CommonResponse(ResponseStatus.SUCCESS, responseData);
    }

    @Override
    public CommonResponse getByRegionName(String regionName) {
        Optional<Region> optionalRegion = regionRepository.findRegionByName(regionName);
        if (optionalRegion.isPresent()) {
            Long id = optionalRegion.get().getId();
            List<Carrier> carriers = carrierRepository.findCarriersByRegionsIdOrderByName(id);
            return new CommonResponse(ResponseStatus.SUCCESS, carriers);
        }
        return new CommonResponse(ResponseStatus.FAILED, "Region with this name not found");
    }
}
