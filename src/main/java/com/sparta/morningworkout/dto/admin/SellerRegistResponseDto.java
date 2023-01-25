package com.sparta.morningworkout.dto.admin;

import com.sparta.morningworkout.entity.SellerRegist;
import lombok.Getter;

@Getter
public class SellerRegistResponseDto {
    private final String username;

    public SellerRegistResponseDto(SellerRegist sellerRegist) {
        this.username = sellerRegist.getUsername();
    }
}