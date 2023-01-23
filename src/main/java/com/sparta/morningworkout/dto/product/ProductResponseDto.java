package com.sparta.morningworkout.dto.product;


import com.sparta.morningworkout.entity.CategoryEnum;
import com.sparta.morningworkout.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductResponseDto {

    private String productName;
    private int price;
    private CategoryEnum categoryEnum;
    public ProductResponseDto(Product product) {
        this.productName = product.getProductName();
        this.price = product.getPrice();
        this.categoryEnum = product.getCategory();

    }


}
