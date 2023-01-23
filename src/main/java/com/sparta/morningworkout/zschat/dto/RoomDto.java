package com.sparta.morningworkout.zschat.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.sparta.morningworkout.zschat.entity.ChatRoom;

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
    private List<MessageDto> messages;

    public RoomDto(ChatRoom room) {
        this.roomId = room.getId();
        this.productId = room.getProduct().getId();
        List<MessageDto> messageList = room.getMessages().stream().map(MessageDto::new).collect(Collectors.toList());
    }
}
