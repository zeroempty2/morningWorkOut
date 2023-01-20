package com.sparta.morningworkout.service;


import com.sparta.morningworkout.dto.chat.ChatRoomDto;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.chat.ChatParticipant;
import com.sparta.morningworkout.entity.chat.ChatRoom;
import com.sparta.morningworkout.entity.chat.Message;
import com.sparta.morningworkout.repository.ChatRoomRepository;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    private final ProfileRepository profileRepository;


    public void save(ChatRoomDto message) {
        Message messages = new Message();


    }


    @Transactional
    public String createRoom(Long productId, User customer) {
        //쿼리가 몇개여...
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지않습니다"));
        User seller = userRepository.findById(product.getUserId()).orElseThrow(() -> new IllegalArgumentException("해당 유저는 존재하지않습니다"));

        Profile profileSeller = profileRepository.findById(seller.getId()).orElseThrow(()->new IllegalArgumentException("해당 프로필은 조회되지 않습니다"));
        Profile profileCustomer = profileRepository.findById(customer.getId()).orElseThrow(()->new IllegalArgumentException("해당 프로필은 조회되지 않습니다"));

        ChatParticipant chatParticipantSeller = ChatParticipant.builder().id(seller.getId()).nickName(profileSeller.getNickname()).build();
        ChatParticipant chatParticipantCustomer = ChatParticipant.builder().id(customer.getId()).nickName(profileCustomer.getNickname()).build();

        List<ChatParticipant> chatParticipants = new ArrayList<>();
        chatParticipants.add(chatParticipantCustomer);
        chatParticipants.add(chatParticipantSeller);
        ChatRoom chatRoom = ChatRoom.builder().product(product).chatParticipants(chatParticipants).build();
        chatRoomRepository.save(chatRoom);
        return "방 생성 완료?";
    }

    @Transactional
    public ChatRoom findRoom(Product product, User customer){
        ChatRoom chatRoom = chatRoomRepository.findByCustomerAndProduct(customer.getId(), product.getId());
        return chatRoom;
    }




}
