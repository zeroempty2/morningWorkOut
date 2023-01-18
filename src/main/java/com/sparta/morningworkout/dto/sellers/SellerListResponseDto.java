package com.sparta.morningworkout.dto.sellers;

import com.sparta.morningworkout.entity.SellerRegist;
import com.sparta.morningworkout.entity.User;
import lombok.Getter;

@Getter
public class SellerListResponseDto {


    private long userId;

    private String username;

    public SellerListResponseDto(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
    }
}
