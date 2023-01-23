package com.sparta.morningworkout.zschat.dto;

import java.util.List;

import com.sparta.morningworkout.zschat.entity.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Long roomId;
    private Long productId;
    private Long senderId;
    private String senderNick;
    private Long receiverId;
    private String receiverNick;
    private List<Message> messages;
}
