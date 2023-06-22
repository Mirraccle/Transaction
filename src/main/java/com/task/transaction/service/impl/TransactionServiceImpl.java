package com.task.transaction.service.impl;

import com.task.transaction.domain.*;
import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.ScoreDto;
import com.task.transaction.dto.TransactionDto;
import com.task.transaction.dto.enums.ResponseStatus;
import com.task.transaction.repository.*;
import com.task.transaction.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final CarrierRepository carrierRepository;

    private final RequestRepository requestRepository;

    private final OfferRepository offerRepository;

    private final RegionRepository regionRepository;

    private final PlaceRepository placeRepository;

    private final static int LOW_SCORE = 1;

    private final static int HIGH_SCORE = 10;


    public TransactionServiceImpl(TransactionRepository transactionRepository, CarrierRepository carrierRepository, RequestRepository requestRepository, OfferRepository offerRepository, RegionRepository regionRepository, PlaceRepository placeRepository) {
        this.transactionRepository = transactionRepository;
        this.carrierRepository = carrierRepository;
        this.requestRepository = requestRepository;
        this.offerRepository = offerRepository;
        this.regionRepository = regionRepository;
        this.placeRepository = placeRepository;
    }

    @Override
    public CommonResponse createTransaction(TransactionDto transactionDto) {
        CommonResponse commonResponse = checkTransaction(transactionDto);
        if (commonResponse != null) {
            return commonResponse;
        }
        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setOfferId(transactionDto.getOfferId());
        transaction.setRequestId(transactionDto.getRequestId());
        Optional<Carrier> optionalCarrier = carrierRepository.findByName(transactionDto.getCarrierName());
        transaction.setCarrierId(optionalCarrier.get().getId());
        transactionRepository.save(transaction);
        return new CommonResponse(ResponseStatus.SUCCESS, transactionDto);
    }

    private CommonResponse checkTransaction(TransactionDto transactionDto) {
        Optional<Carrier> optionalCarrier = carrierRepository.findByName(transactionDto.getCarrierName());
        if (optionalCarrier.isEmpty()) {
            return new CommonResponse(ResponseStatus.FAILED, "Carrier with this name not found");
        }

        Optional<Request> optionalRequest = requestRepository.findById(transactionDto.getRequestId());
        if (optionalRequest.isEmpty()) {
            return new CommonResponse(ResponseStatus.FAILED, "Request with this id not found");
        }

        Optional<Offer> optionalOffer = offerRepository.findById(transactionDto.getOfferId());
        if (optionalOffer.isEmpty()) {
            return new CommonResponse(ResponseStatus.FAILED, "Offer with this id not found");
        }

        Request request = optionalRequest.get();
        Offer offer = optionalOffer.get();
        if (!request.getProductId().equals(offer.getProductId())) {
            return new CommonResponse(ResponseStatus.FAILED, "Offer and request related to different products+");
        }

        List<Region> regionsByCarriersId = regionRepository.findRegionsByCarriersId(optionalCarrier.get().getId());
        Optional<Place> requestPlace = placeRepository.findById(request.getPlaceId());
        Optional<Place> offerPlace = placeRepository.findById(offer.getPlaceId());
        if (requestPlace.isPresent() && offerPlace.isPresent()) {
            List<Long> regionIds = regionsByCarriersId.stream().map(Region::getId)
                    .toList();
            if (!regionIds.contains(requestPlace.get().getRegionId()) ||
                    !regionIds.contains(offerPlace.get().getRegionId())) {
                return new CommonResponse(ResponseStatus.FAILED, "Carrier is not related to the place");
            }
        }
        if (transactionRepository.existsByIdOrOfferIdOrRequestId(
                transactionDto.getId(),
                transactionDto.getOfferId(),
                transactionDto.getRequestId())) {
            return new CommonResponse(ResponseStatus.FAILED, "Given data relates to other transaction");
        }
        return null;
    }

    @Override
    public CommonResponse evaluate(ScoreDto scoreDto) {
        Optional<Transaction> optionalTransaction = transactionRepository.findById(scoreDto.getTransactionId());
        if (optionalTransaction.isPresent()) {
            Transaction transaction = optionalTransaction.get();
            transaction.setScore(scoreDto.getScore());
            transactionRepository.save(transaction);
            if (scoreDto.getScore() >= LOW_SCORE && scoreDto.getScore() <= HIGH_SCORE) {
                return new CommonResponse(ResponseStatus.SUCCESS, Boolean.TRUE);
            }
            return new CommonResponse(ResponseStatus.SUCCESS, Boolean.FALSE);
        }
        return new CommonResponse(ResponseStatus.FAILED, "Transaction with given id not found");
    }
}
