package com.task.transaction.controller;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.ScoreDto;
import com.task.transaction.dto.TransactionDto;
import com.task.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/transaction")
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> addTransaction(@Valid @RequestBody TransactionDto transactionDto) {
        log.info("Rest request for creating transaction: {}", transactionDto.getId());
        return ResponseEntity.ok(transactionService.createTransaction(transactionDto));
    }

    @PostMapping("/evaluate")
    public ResponseEntity<CommonResponse> evaluateTransaction(@Valid @RequestBody ScoreDto scoreDto) {
        log.info("Rest request for evaluating transaction: {}", scoreDto.getTransactionId());
        return ResponseEntity.ok(transactionService.evaluate(scoreDto));
    }
}
