package com.sparta.morningworkout.zschat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.morningworkout.zschat.dto.MessageDto;
import com.sparta.morningworkout.zschat.service.MessageService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final MessageService messageService;

    // 메시지 보내기
    @MessageMapping("/chat/send")
    public void sendMsg(@Payload MessageDto message, @AuthenticationPrincipal UserDetails userDetails) {
        String receiver = message.getReceiver();
        log.info(receiver);
        messageService.save(message, userDetails.getUsername());

        simpMessagingTemplate.convertAndSendToUser(receiver, "/queue", message);
        // simpMessagingTemplate.convertAndSend("/topic/" + receiver,message);
    }
}
