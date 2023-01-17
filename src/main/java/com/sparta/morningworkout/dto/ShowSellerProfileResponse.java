package com.sparta.morningworkout.dto;

import com.sparta.morningworkout.entity.CategoryEnum;
import com.sparta.morningworkout.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowSellerProfileResponse {
    private String nickname;
//    private String image;
    private String infoContent;
    private CategoryEnum category;

    public ShowSellerProfileResponse(Profile profile) {
        this.nickname = profile.getNickname();
        this.infoContent = profile.getInfoContent();
        this.category = profile.getCategory();
    }
}