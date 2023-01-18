package com.sparta.morningworkout.dto.profile;

import com.sparta.morningworkout.entity.Profile;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowCustomerProfileResponseDto {
    private String nickname;
//    private String image;
    public ShowCustomerProfileResponseDto(Profile profile) {
        this.nickname = profile.getNickname();
    }
}
