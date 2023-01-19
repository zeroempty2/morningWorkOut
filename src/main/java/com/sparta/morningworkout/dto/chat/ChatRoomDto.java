package com.sparta.morningworkout.dto.chat;

import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.chat.ChatRoom;
import com.sparta.morningworkout.entity.chat.Message;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class ChatRoomDto {

    private Long roomId;
    private User sellerId;
    private User customerId;
    private List<Message> messages;
}
