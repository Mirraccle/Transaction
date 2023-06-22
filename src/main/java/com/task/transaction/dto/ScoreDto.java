package com.task.transaction.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ScoreDto {
    @NotBlank
    private String transactionId;

    @NotNull
    private Integer score;
}
