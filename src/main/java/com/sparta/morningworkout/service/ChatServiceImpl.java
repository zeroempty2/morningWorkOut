package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.chat.ChatRequestDto;
import com.sparta.morningworkout.dto.chat.ChatResponseDto;
import com.sparta.morningworkout.entity.Chat;
import com.sparta.morningworkout.entity.ChatRoom;
import com.sparta.morningworkout.repository.ChatRepository;
import com.sparta.morningworkout.repository.ChatRoomRepository;
import com.sparta.morningworkout.service.serviceInterface.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatServiceImpl implements ChatService {
    private final ChatRepository chatRepository;
    private final ChatRoomRepository chatRoomRepository;
    @Override
    @Transactional
    public StatusResponseDto sendChat(ChatRequestDto chatRequestDto, long senderId,long chatRoomId){
       ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(
               () -> new IllegalArgumentException("존재하지 않는 채팅방입니다")
       );
       if(chatRoom.isClosed()){
           return new StatusResponseDto(400,"종료된 채팅방입니다.");
       }
        Chat chat = Chat.builder().userId(senderId).contents(chatRequestDto.getMessage()).chatRoomId(chatRoomId).build();
        chatRepository.save(chat);
        return new StatusResponseDto(200,"채팅 저장 완료");
    }
    @Override
    @Transactional
    public List<ChatResponseDto> loadChat(long userId, long chatRoomId) {
        ChatRoom chatroom = chatRoomRepository.findById(chatRoomId).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않은 채팅방입니다.")
        );
        if(chatroom.getSendUserId() != userId && chatroom.getReceiveUserId() != userId){
            throw new IllegalArgumentException("유효하지 않은 유저id입니다");
        }
       return chatRepository.findALLChatByChatRoom(chatRoomId);
    }


}
