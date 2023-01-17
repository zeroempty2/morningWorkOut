package com.sparta.morningworkout.dto;

import com.sparta.morningworkout.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowCustomerProfileResponse {
    private String nickname;
//    private String image;
    public ShowCustomerProfileResponse(Profile profile) {
        this.nickname = profile.getNickname();
    }
}
