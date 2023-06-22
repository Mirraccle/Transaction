package com.task.transaction.service;

import com.task.transaction.dto.CarrierDto;
import com.task.transaction.dto.CommonResponse;

public interface CarrierService {
    CommonResponse createCarrier(CarrierDto carrierDto);

    CommonResponse getByRegionName(String regionName);
}
