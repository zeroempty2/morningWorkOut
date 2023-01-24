package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.chat.ChatRequestDto;
import com.sparta.morningworkout.dto.chat.ChatResponseDto;

import java.util.List;

public interface ChatService {
    StatusResponseDto sendChat(ChatRequestDto chatRequestDto, long senderId,long chatRoomId);
    List<ChatResponseDto> loadChat(long userId, long chatRoomId);
}
