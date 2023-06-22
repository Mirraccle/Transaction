package com.task.transaction.service.impl;

import com.task.transaction.domain.Offer;
import com.task.transaction.domain.Place;
import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.OfferDto;
import com.task.transaction.dto.enums.ResponseStatus;
import com.task.transaction.repository.OfferRepository;
import com.task.transaction.repository.PlaceRepository;
import com.task.transaction.repository.ProductRepository;
import com.task.transaction.service.OfferService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    private final PlaceRepository placeRepository;

    private final ProductRepository productRepository;

    public OfferServiceImpl(OfferRepository offerRepository, PlaceRepository placeRepository, ProductRepository productRepository) {
        this.offerRepository = offerRepository;
        this.placeRepository = placeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CommonResponse addOffer(OfferDto offerDto) {
        if (offerRepository.existsById(offerDto.getId())) {
            return new CommonResponse(ResponseStatus.FAILED, "Request with this id is already exists");
        }

        if (!productRepository.existsById(offerDto.getProductId())) {
            return new CommonResponse(ResponseStatus.FAILED, "Product with this id not found");
        }

        Optional<Place> optionalPlace = placeRepository.findByName(offerDto.getPlaceName());
        if (optionalPlace.isPresent()) {
            Long placeId = optionalPlace.get().getId();
            Offer offer = new Offer();
            offer.setId(offerDto.getId());
            offer.setPlaceId(placeId);
            offer.setProductId(offerDto.getProductId());
            offerRepository.save(offer);
            return new CommonResponse(ResponseStatus.SUCCESS, offerDto);
        }

        return new CommonResponse(ResponseStatus.FAILED, "Place with this name not found");
    }
}
