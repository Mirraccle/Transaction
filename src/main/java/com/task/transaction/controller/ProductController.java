package com.task.transaction.controller;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.ProductDto;
import com.task.transaction.service.ProductService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/product")
@Slf4j
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create")
    public ResponseEntity<CommonResponse> createProduct(@Valid @RequestBody ProductDto productDto) {
        log.info("Rest request for creating product: {}", productDto.getName());
        return ResponseEntity.ok(productService.createProduct(productDto));
    }
}
