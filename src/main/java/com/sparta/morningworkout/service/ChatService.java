package com.sparta.morningworkout.service;

import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.chat.ChatRoom;
import com.sparta.morningworkout.repository.ChatRoomRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;

    // 전체 채팅방 찾기
    @Transactional
    public List<ChatRoom> findAllRoom(){
        List<ChatRoom> chatRooms = chatRoomRepository.findAll();
        return chatRooms;
    }

    @Transactional
    public ChatRoom findChatRoom(Long roomId){
        ChatRoom chatRooms = chatRoomRepository.findById(roomId).orElseThrow(() -> new IllegalArgumentException("해당 채팅방은 존재하지 않습니다"));
        return chatRooms;
    }

    @Transactional
    public ChatRoom createRoom(User customer,User seller ,Product product) {
        ChatRoom chatRoom = ChatRoom.create(customer, seller, product);
        chatRoomRepository.save(chatRoom);
        return chatRoom;
    }

}
