package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.entity.ChatRoom;
import com.sparta.morningworkout.repository.ChatRoomRepository;
import com.sparta.morningworkout.service.serviceInterface.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatroomService {
    private final ChatRoomRepository chatRoomRepository;
    @Override
    @Transactional
    public StatusResponseDto createChatRoom(long senderId, long receiverId) {
        ChatRoom chatroom = ChatRoom.builder().sendUserId(senderId).receiveUserId(receiverId).build();
        chatRoomRepository.save(chatroom);
        return new StatusResponseDto(200,"채팅방 생성 완료");
    }
    @Override
    @Transactional
    public StatusResponseDto closeChatRoom(long userId, long chatRoomId) {
        ChatRoom chatroom = chatRoomRepository.findById(chatRoomId).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않은 채팅방입니다.")
        );
        if(chatroom.getSendUserId() != userId && chatroom.getReceiveUserId() != userId){
            return new StatusResponseDto(400,"유효하지 않은 유저입니다.");
        }
        chatroom.closeChatRoom();
        return new StatusResponseDto(200,"채팅 종료, 채팅을 더이상 보낼 수 없습니다.");
    }



}
