package com.sparta.morningworkout.dto;

import com.sparta.morningworkout.entity.CategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateSellerProfileRequest {

    private String nickname;
    private String infoContent;
    private CategoryEnum category;
}
