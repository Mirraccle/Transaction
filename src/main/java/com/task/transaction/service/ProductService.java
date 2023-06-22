package com.task.transaction.service;

import com.task.transaction.dto.CommonResponse;
import com.task.transaction.dto.ProductDto;

public interface ProductService {

    CommonResponse createProduct(ProductDto productDto);
}
