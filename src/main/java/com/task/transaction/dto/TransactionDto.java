package com.task.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TransactionDto {

    @NotBlank
    private String id;

    @NotBlank
    private String carrierName;

    @NotBlank
    private String requestId;

    @NotBlank
    private String offerId;
}
