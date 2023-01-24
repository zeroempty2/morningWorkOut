package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.ChatRoomDto;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.chat.ChatRoom;
import com.sparta.morningworkout.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    // 전체 채팅방 찾기
    @Transactional
    public List<ChatRoomDto> findAllRoom(){
        List<ChatRoomDto> chatRooms = chatRoomRepository.findAll().stream().map(ChatRoomDto::new).toList();
        return chatRooms;
    }

    @Transactional
    public ChatRoomDto findChatRoom(Long roomId){
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("해당 채팅방은 존재하지 않습니다"));
        ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom);
        return chatRoomDto;
    }

    @Transactional
    public ChatRoom createRoom(User customer,User seller ,Product product) {
        ChatRoom chatRoom = ChatRoom.create(customer, seller, product);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }


    public ChatRoomDto findById(Long roomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("해당 채팅방 존재 ㄴㄴ 다시 조회 ㄱㄱ"));
        ChatRoomDto chatRoomDto = new ChatRoomDto(chatRoom);
        return chatRoomDto;
    }
}
