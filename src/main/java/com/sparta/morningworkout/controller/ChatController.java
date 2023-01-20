package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.chat.ChatRoomDto;
import com.sparta.morningworkout.dto.chat.MessageDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.ChatRoomService;
import com.sparta.morningworkout.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin // cors 관련
@RequestMapping("/chat") // -> /app/chat
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;


//    @MessageMapping - message
//    @PostMapping - room

    /**
     * 1. 방 만들기 createRoom -> 상품, 유저(구매자) -> postMapping
     * 2. 메시지 보내기 sendMessage - m
     * 3. 메시지 확인하기 checkMessage - m
     * 4. 방 찾기 findRoom - get
     * 5. 방 나가기 deleteRoom - delete
     */


    @MessageMapping("/hello") // HTTP 프로토콜 메서드 매핑, 메시지 메핑은 웹소켓 기반으로 다른 통신 방법,
    // 상훈 어플리케이션 , 메시지를 주고받을때, 보내는 - 받는 바로 진행이 되는 것이 아닌
    @SendTo("/topic/greeting")



    @PostMapping("/room/{productId}") // 상품을 통해 접근을 하게되니까 아무래도 roomId보단 상품아이디가 더 좋지않을까 하여...
    public ResponseEntity createRoom(@PathVariable Long productId, @AuthenticationPrincipal UserDetailsImpl customer) {

        String msg = chatRoomService.createRoom(productId, customer.getUser());

    }

    @MessageMapping("/message")
    public ResponseEntity<MessageDto> sendMessage(@Payload ChatRoomDto message, ChatRoomDto chatRoom, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User sender = userDetails.getUser();
        chatRoomService.save(message);
        messagingTemplate.convertAndSend("/room." + sender, chatRoom.getRoomId());
        return ResponseEntity.status(HttpStatus.OK).body()
    }


//    @SubscribeMapping("/room.{roomId}")
//    public MessageDto chatInRoom(@Payload MessageDto message, @DestinationVariable String roomId) {
//        return
//    }

    @MessageMapping("/")
    public void checkMessage() {

    }

    @GetMapping("/")
    public void findRoom() {

    }

    @DeleteMapping("/")
    public void deleteRoom() {

    }
}
