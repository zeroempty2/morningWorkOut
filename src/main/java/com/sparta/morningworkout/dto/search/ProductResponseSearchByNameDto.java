package com.sparta.morningworkout.dto.search;

import com.sparta.morningworkout.entity.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProductResponseSearchByNameDto {

    private String productName;
    private int price;
    private String username;
    private CategoryEnum categoryEnum;
}
