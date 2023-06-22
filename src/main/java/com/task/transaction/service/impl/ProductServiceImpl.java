package com.task.transaction.service.impl;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.ProductDto;
import com.task.transaction.dto.enums.ResponseStatus;
import com.task.transaction.repository.ProductRepository;
import com.task.transaction.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public CommonResponse createProduct(ProductDto productDto) {
        if (productRepository.existsById(productDto.getProductId())) {
            return new CommonResponse(ResponseStatus.FAILED, "Product with this id is already exists");
        }
        productRepository.save(productDto.toEntity());
        return new CommonResponse(ResponseStatus.SUCCESS, productDto);
    }
}
