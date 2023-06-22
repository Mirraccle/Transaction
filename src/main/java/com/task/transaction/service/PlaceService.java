package com.task.transaction.service;

import com.task.transaction.domain.Place;
import com.task.transaction.dto.PlaceDto;
import com.task.transaction.dto.RegionDto;

import java.util.List;

public interface PlaceService {

    List<PlaceDto> createPlaces(RegionDto regionDto, Long regionId);
}
