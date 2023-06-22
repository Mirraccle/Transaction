package com.task.transaction.dto;

import lombok.Data;

@Data
public class ProductTransactionNumberDto {

    private String productId;

    private Integer transactionCount;
}
