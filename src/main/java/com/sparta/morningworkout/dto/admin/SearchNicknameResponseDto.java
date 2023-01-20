package com.sparta.morningworkout.dto.admin;

import lombok.Getter;

@Getter
public class SearchNicknameResponseDto {
    private String username;
    private String nickname;

    public SearchNicknameResponseDto(String username, String nickname) {
        this.username = username;
        this.nickname = nickname;
    }
}
