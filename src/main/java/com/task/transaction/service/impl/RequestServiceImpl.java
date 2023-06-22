package com.task.transaction.service.impl;

import com.task.transaction.domain.Place;
import com.task.transaction.domain.Request;
import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.RequestDto;
import com.task.transaction.dto.enums.ResponseStatus;
import com.task.transaction.repository.PlaceRepository;
import com.task.transaction.repository.ProductRepository;
import com.task.transaction.repository.RequestRepository;
import com.task.transaction.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    private final PlaceRepository placeRepository;

    private final ProductRepository productRepository;

    public RequestServiceImpl(RequestRepository requestRepository, PlaceRepository placeRepository, ProductRepository productRepository) {
        this.requestRepository = requestRepository;
        this.placeRepository = placeRepository;
        this.productRepository = productRepository;
    }

    @Override
    public CommonResponse addRequest(RequestDto requestDto) {
        if (requestRepository.existsById(requestDto.getId())) {
            return new CommonResponse(ResponseStatus.FAILED, "Request with this id is already exists");
        }

        if (!productRepository.existsById(requestDto.getProductId())) {
            return new CommonResponse(ResponseStatus.FAILED, "Product with this id not found");
        }

        Optional<Place> optionalPlace = placeRepository.findByName(requestDto.getPlaceName());
        if (optionalPlace.isPresent()) {
            Long placeId = optionalPlace.get().getId();
            Request request = new Request();
            request.setId(requestDto.getId());
            request.setPlaceId(placeId);
            request.setProductId(requestDto.getProductId());
            requestRepository.save(request);
            return new CommonResponse(ResponseStatus.SUCCESS, requestDto);
        }

        return new CommonResponse(ResponseStatus.FAILED, "Place with this name not found");
    }
}
