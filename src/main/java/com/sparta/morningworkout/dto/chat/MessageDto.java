package com.sparta.morningworkout.dto.chat;

import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.chat.ChatRoom;
import com.sparta.morningworkout.entity.chat.Message;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private Long roomId;
    private User Sender;
    private String content;
    private LocalDateTime time;


    public MessageDto(Message message) {
        this.roomId = message.getChatRoom().getRoomId();
        this.Sender = message.getSender();
        this.content = message.getContent();
        this.time = message.getSendTime();
    }
}
