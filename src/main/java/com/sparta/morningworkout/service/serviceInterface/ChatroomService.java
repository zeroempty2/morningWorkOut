package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.StatusResponseDto;


public interface ChatroomService {
    StatusResponseDto createChatRoom(long senderId, long receiverId);
    StatusResponseDto closeChatRoom(long userId, long chatRoomId);

}
