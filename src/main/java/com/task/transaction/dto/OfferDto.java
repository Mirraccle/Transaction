package com.task.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OfferDto {

    @NotBlank
    private String id;

    @NotBlank
    private String productId;

    @NotBlank
    private String placeName;
}
