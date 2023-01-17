package com.sparta.morningworkout.dto.product;

import com.sparta.morningworkout.entity.CategoryEnum;
import lombok.Getter;


@Getter
public class ProductRequestDto {

    private String productName;
    private int price;
    private CategoryEnum categoryEnum;

}
