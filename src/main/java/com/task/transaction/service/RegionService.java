package com.task.transaction.service;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.RegionDto;

public interface RegionService {

    CommonResponse createRegion(RegionDto regionDto);
}
