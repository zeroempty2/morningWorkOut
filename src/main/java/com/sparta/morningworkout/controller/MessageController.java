package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.entity.chat.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat/message")
    public void enter(Message message) {
        if (Message.MessageType.ENTER.equals(message.getType())) {
            message.setMessage(message.getSender()+"님이 입장하였습니다");
        }
        messagingTemplate.convertAndSend("/topic/chat/room/"+message.getRoomId(),message);
    }
}
