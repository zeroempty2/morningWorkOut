package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.chat.MessageDto;
import com.sparta.morningworkout.entity.chat.Message;
import com.sparta.morningworkout.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MessageService {

    private final MessageRepository messageRepository;

}
