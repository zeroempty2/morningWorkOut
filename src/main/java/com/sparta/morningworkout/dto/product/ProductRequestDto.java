package com.sparta.morningworkout.dto.product;

import com.sparta.morningworkout.entity.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequestDto {

    private String productName;
    private int price;
    private CategoryEnum categoryEnum;

}
