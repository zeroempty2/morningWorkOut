package com.sparta.morningworkout.dto.admin;

import lombok.Getter;

@Getter
public class UserContentsResponseDto {
    private String username;
    private String nickname;

    public UserContentsResponseDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}
