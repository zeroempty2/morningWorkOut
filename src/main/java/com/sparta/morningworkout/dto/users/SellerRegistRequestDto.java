package com.sparta.morningworkout.dto.users;

import com.sparta.morningworkout.entity.CategoryEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SellerRegistRequestDto {
    private String infoContent;
    private CategoryEnum category;
}
