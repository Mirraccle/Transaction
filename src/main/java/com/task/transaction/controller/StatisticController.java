package com.task.transaction.controller;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.service.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/statistic")
@Slf4j
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/delivery-number")
    public ResponseEntity<CommonResponse> getDeliveryNumberByRegions() {
        return ResponseEntity.ok(statisticService.getRegionDeliveryNumbers());
    }

    @GetMapping("/carrier-score")
    public ResponseEntity<CommonResponse> getCarriersScore(@RequestParam Integer minimumScore) {
        return ResponseEntity.ok(statisticService.getScorePerCarrier(minimumScore));
    }

    @GetMapping("/product-transactions")
    public ResponseEntity<CommonResponse> getProductTransactionCount() {
        return ResponseEntity.ok(statisticService.countProductTransactionNumbers());
    }
}
