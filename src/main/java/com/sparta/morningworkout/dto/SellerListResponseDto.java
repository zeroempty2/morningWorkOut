package com.sparta.morningworkout.dto;

import com.sparta.morningworkout.entity.SellerRegistList;
import lombok.Getter;

@Getter
public class SellerListResponseDto {

    private long id;

    private long userId;

    public SellerListResponseDto(SellerRegistList sellerRegistList) {
        this.id = sellerRegistList.getId();
        this.userId = sellerRegistList.getUserId();
    }
}
