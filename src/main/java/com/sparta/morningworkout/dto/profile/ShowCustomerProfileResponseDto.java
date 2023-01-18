package com.sparta.morningworkout.dto.profile;

import com.sparta.morningworkout.entity.Profile;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowCustomerProfileResponseDto {
    private long id;
    private String nickname;
//    private String image;
    public ShowCustomerProfileResponseDto(long id, Profile profile) {
        this.id = id;
        this.nickname = profile.getNickname();
    }

}
