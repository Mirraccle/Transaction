package com.task.transaction.error;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.enums.ResponseStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler
    public ResponseEntity<CommonResponse> handleValidException(MethodArgumentNotValidException ex) {
        log.error("--> Handled ValidException. Type: {}, Message: {}.", ex.getClass(), ex.getMessage());
        return ResponseEntity.badRequest().body(new CommonResponse(ResponseStatus.FAILED, "Enter valid data"));
    }

    @ExceptionHandler
    public ResponseEntity<CommonResponse> handleException(Exception ex) {
        log.error("--> Handled Exception. Type: {}, Message: {}.", ex.getClass(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new CommonResponse(ResponseStatus.FAILED, "Unknown error"));
    }
}
