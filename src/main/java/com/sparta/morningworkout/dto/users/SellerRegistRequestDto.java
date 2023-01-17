package com.sparta.morningworkout.dto.users;

import com.sparta.morningworkout.entity.CategoryEnum;
import lombok.Getter;

@Getter
public class SellerRegistRequestDto {
    private String infocontent;
    private CategoryEnum categoryEnum;
}
