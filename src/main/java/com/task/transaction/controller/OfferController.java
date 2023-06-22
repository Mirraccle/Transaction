package com.task.transaction.controller;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.OfferDto;
import com.task.transaction.dto.RequestDto;
import com.task.transaction.service.OfferService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/offer")
@Slf4j
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> addOffer(@Valid @RequestBody OfferDto offerDto) {
        log.info("Rest request for creating request: {}", offerDto.getId());
        return ResponseEntity.ok(offerService.addOffer(offerDto));
    }
}
