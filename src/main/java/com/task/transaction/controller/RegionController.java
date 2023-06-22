package com.task.transaction.controller;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.RegionDto;
import com.task.transaction.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/region")
@Slf4j
public class RegionController {

    private final RegionService regionService;

    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> addRegion(@RequestBody RegionDto regionDto) {
        log.info("Rest request for creating region: {}", regionDto.getRegionName());
        return ResponseEntity.ok(regionService.createRegion(regionDto));
    }
}
