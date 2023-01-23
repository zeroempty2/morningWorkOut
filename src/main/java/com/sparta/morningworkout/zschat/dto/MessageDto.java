package com.sparta.morningworkout.zschat.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long chatRoomId;
    private String receiver;
    private String sender;
    private String message;
}