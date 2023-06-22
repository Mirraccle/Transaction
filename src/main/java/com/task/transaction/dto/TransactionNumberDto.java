package com.task.transaction.dto;

import lombok.Data;

import java.util.List;

@Data
public class TransactionNumberDto {

    private Integer transactionNumber;
    private List<RegionNameDto> regions;
}
