package com.sparta.morningworkout.dto;


import com.sparta.morningworkout.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {

    private String productName;
    private int price;

    public ProductResponseDto(Product product) {
        this.productName = product.getProductName();
        this.price = product.getPrice();

    }
}
