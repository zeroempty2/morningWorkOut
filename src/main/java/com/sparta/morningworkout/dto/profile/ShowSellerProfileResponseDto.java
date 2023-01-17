package com.sparta.morningworkout.dto.profile;

import com.sparta.morningworkout.entity.CategoryEnum;
import com.sparta.morningworkout.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowSellerProfileResponseDto {
    private String nickname;
//    private String image;
    private String infoContent;
    private CategoryEnum category;

    public ShowSellerProfileResponseDto(Profile profile) {
        this.nickname = profile.getNickname();
        this.infoContent = profile.getInfoContent();
        this.category = profile.getCategory();
    }
}