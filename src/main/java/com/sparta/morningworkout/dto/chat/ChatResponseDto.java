package com.sparta.morningworkout.dto.chat;

import lombok.Getter;

@Getter
public class ChatResponseDto {
    private long userId;
    private String nickname;
    private String message;

    public ChatResponseDto(long userId, String nickname, String message) {
        this.userId = userId;
        this.nickname = nickname;
        this.message = message;
    }
}
