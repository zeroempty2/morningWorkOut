package com.sparta.morningworkout.dto.sellers;

import com.sparta.morningworkout.entity.SellerRegist;
import lombok.Getter;

@Getter
public class SellerListResponseDto {

    private long id;

    private long userId;

    public SellerListResponseDto(SellerRegist sellerRegist) {
        this.id = sellerRegist.getId();
        this.userId = sellerRegist.getUserId();
    }
}
