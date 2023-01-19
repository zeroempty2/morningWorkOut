package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.chat.ChatRoomDto;
import com.sparta.morningworkout.dto.chat.MessageDto;
import com.sparta.morningworkout.service.ChatRoomService;
import com.sparta.morningworkout.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin // cors 관련
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatRoomService chatRoomService;
    private final MessageService messageService;

//    @MessageMapping("/hello") // requestMapping과 비슷한 역할, webSocketConfig에서 설정한 목적지prefix에 맞춰서 "/api/hello"라는 목적지를 가진 uri들이 해당 핸들러로 들어옴
//    @SendTo("/user/greeting") // 핸들러에서 처리를 마친 값들을 "/user/greeting"이라는 값으로 보낸다는 의미, 이때 앞에 붙은 user로 인해 다시 simpleBroker로 전달

    @MessageMapping("/chat/send") // 메시지를 보내는 역할
    public void chat(MessageDto.Send message) {
        messageService.sendMessage(message);
        messagingTemplate.convertAndSend("/topic/chat" + message.getReceiveredId(), message);
    }

    @PostMapping("/chat/room")
    public ResponseEntity JoinChatRoom(@RequestBody ChatRoomDto requestDto) {
        try {
            Long roomId = chatRoomService.joinChatRoom(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Result<>(roomId));
        } catch(IllegalStateException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage(), "400"));
        }
    }


    @GetMapping("/chat/room") // 채팅방 찾기
    public ResponseEntity getChatRoomList() {

    }

    @DeleteMapping("/chat/room")
    public ResponseEntity deleteChatRoom(){

    }
}
