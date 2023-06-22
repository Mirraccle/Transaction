package com.task.transaction.dto;

import com.task.transaction.domain.Place;
import lombok.Data;

import java.util.List;

@Data
public class RegionDto {

    private String regionName;

    private List<PlaceDto> places;
}
