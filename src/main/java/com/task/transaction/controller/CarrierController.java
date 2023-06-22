package com.task.transaction.controller;

import com.task.transaction.dto.CarrierDto;
import com.task.transaction.dto.CommonResponse;
import com.task.transaction.service.CarrierService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/carrier")
@Slf4j
public class CarrierController {

    private final CarrierService carrierService;

    public CarrierController(CarrierService carrierService) {
        this.carrierService = carrierService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> addCarrier(@RequestBody CarrierDto carrierDto) {
        log.info("Rest request for creating carrier: {}", carrierDto.getName());
        return ResponseEntity.ok(carrierService.createCarrier(carrierDto));
    }

    @GetMapping("/get-by-region")
    public ResponseEntity<CommonResponse> getByRegion(@RequestParam String regionName) {
        log.info("Rest request for getting carrier by region: {}", regionName);
        return ResponseEntity.ok(carrierService.getByRegionName(regionName));
    }
}
