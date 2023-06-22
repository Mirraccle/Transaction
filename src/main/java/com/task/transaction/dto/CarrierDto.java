package com.task.transaction.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarrierDto {
    private String name;
    private List<RegionNameDto> regions;
}
