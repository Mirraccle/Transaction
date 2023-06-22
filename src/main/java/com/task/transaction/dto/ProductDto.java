package com.task.transaction.dto;

import com.task.transaction.domain.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductDto {
    @NotBlank
    private String productId;

    @NotBlank
    private String name;

    public Product toEntity() {
        Product product = new Product();
        product.setId(this.getProductId());
        product.setName(this.getName());
        return product;
    }
}
