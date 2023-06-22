package com.task.transaction.service;

import com.task.transaction.domain.*;
import com.task.transaction.dto.*;
import com.task.transaction.dto.enums.ResponseStatus;
import com.task.transaction.repository.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StatisticService {

    private final RegionRepository regionRepository;

    private final PlaceRepository placeRepository;

    private final RequestRepository requestRepository;

    private final TransactionRepository transactionRepository;

    private final CarrierRepository carrierRepository;

    private final ProductRepository productRepository;

    public StatisticService(RegionRepository regionRepository,
                            PlaceRepository placeRepository,
                            RequestRepository requestRepository,
                            TransactionRepository transactionRepository,
                            CarrierRepository carrierRepository,
                            ProductRepository productRepository) {
        this.regionRepository = regionRepository;
        this.placeRepository = placeRepository;
        this.requestRepository = requestRepository;
        this.transactionRepository = transactionRepository;
        this.carrierRepository = carrierRepository;
        this.productRepository = productRepository;
    }

    public CommonResponse getRegionDeliveryNumbers() {
        List<Region> regions = regionRepository.findAll();

        List<TransactionNumberDto> transactionNumbers = new ArrayList<>();

        for (Region region : regions) {
            List<Place> places = placeRepository.findAllByRegionId(region.getId());
            int transactionCount = 0;

            for (Place place : places) {
                List<Request> requests = requestRepository.findAllByPlaceId(place.getId());
                for (Request request : requests) {
                    if (transactionRepository.existsByRequestId(request.getId())) {
                        transactionCount++;
                    }
                }
            }

            if (!transactionNumbers.stream().map(TransactionNumberDto::getTransactionNumber)
                    .toList().contains(transactionCount)) {
                RegionNameDto regionNameDto = new RegionNameDto();
                regionNameDto.setRegionName(region.getName());
                TransactionNumberDto transactionNumberDto = new TransactionNumberDto();
                transactionNumberDto.setTransactionNumber(transactionCount);
                transactionNumberDto.setRegions(List.of(regionNameDto));
                transactionNumbers.add(transactionNumberDto);
            } else {
                int finalTransactionCount = transactionCount;
                Optional<TransactionNumberDto> optionalTransactionNumberDto = transactionNumbers.stream()
                        .filter(transactionNumberDto -> transactionNumberDto.getTransactionNumber() == finalTransactionCount)
                        .findFirst();
                if (optionalTransactionNumberDto.isPresent()) {
                    RegionNameDto regionNameDto = new RegionNameDto();
                    regionNameDto.setRegionName(region.getName());
                    TransactionNumberDto transactionNumberDto = optionalTransactionNumberDto.get();
                    transactionNumberDto.getRegions().add(regionNameDto);
                }
            }

        }

        return new CommonResponse(ResponseStatus.SUCCESS, transactionNumbers);
    }

    public CommonResponse getScorePerCarrier(Integer minimumScore) {

        List<CarrierScoreDto> carrierScoreDtos = new ArrayList<>();

        List<Carrier> carriers = carrierRepository.findAll();

        for (Carrier carrier : carriers) {
            int carrierScore = 0;
            List<Transaction> transactions = transactionRepository.findAllByCarrierId(carrier.getId());
            for (Transaction transaction : transactions) {
                if (transaction.getScore() > minimumScore) {
                    carrierScore += transaction.getScore();
                }
            }
            if (carrierScore > 0) {
                CarrierScoreDto carrierScoreDto = new CarrierScoreDto();
                carrierScoreDto.setCarrierName(carrier.getName());
                carrierScoreDto.setScore(carrierScore);
                carrierScoreDtos.add(carrierScoreDto);
            }
        }
        List<CarrierScoreDto> resultList = carrierScoreDtos.stream()
                .sorted(Comparator.comparing(CarrierScoreDto::getCarrierName))
                .toList();
        return new CommonResponse(ResponseStatus.SUCCESS, resultList);
    }

    public CommonResponse countProductTransactionNumbers() {
        List<Product> products = productRepository.findAll();
        List<ProductTransactionNumberDto> result = new ArrayList<>();
        for (Product product : products) {
            int transactionCount = 0;
            List<Request> requests = requestRepository.findAllByProductId(product.getId());
            for (Request request : requests) {
                if (transactionRepository.existsByRequestId(request.getId())) {
                    transactionCount++;
                }
            }
            ProductTransactionNumberDto productTransactionNumberDto = new ProductTransactionNumberDto();
            productTransactionNumberDto.setProductId(product.getId());
            productTransactionNumberDto.setTransactionCount(transactionCount);
            result.add(productTransactionNumberDto);
        }

        return new CommonResponse(ResponseStatus.SUCCESS, result);
    }
}
