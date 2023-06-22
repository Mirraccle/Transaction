package com.task.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestDto {

    @NotBlank
    private String id;

    @NotBlank
    private String productId;

    @NotBlank
    private String placeName;
}
