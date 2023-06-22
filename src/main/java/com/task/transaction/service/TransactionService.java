package com.task.transaction.service;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.ScoreDto;
import com.task.transaction.dto.TransactionDto;

public interface TransactionService {

    CommonResponse createTransaction(TransactionDto transactionDto);

    CommonResponse evaluate(ScoreDto scoreDto);
}
