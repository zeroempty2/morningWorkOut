package com.sparta.morningworkout.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.morningworkout.dto.chat.MessageDto;
import com.sparta.morningworkout.entity.chat.Message;
import com.sparta.morningworkout.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@RequiredArgsConstructor
@Service
@Slf4j
public class MessageService {

    private final MessageRepository messageRepository;
    private final ObjectMapper objectMapper;
    @Transactional
    public <T> void sendMessage(WebSocketSession session, T message) {
        try {
            session.sendMessage(new TextMessage((objectMapper.writeValueAsString(message))));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

}
