package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.serviceInterface.ChatroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chatroom")
public class ChatRoomController {
    private final ChatroomService chatroomService;
    @PostMapping("/{receiverId}")
    public ResponseEntity<StatusResponseDto> createChatRoom(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable long receiverId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        StatusResponseDto statusResponseDto = chatroomService.createChatRoom(userDetails.getUserId(),receiverId);
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }
    @PostMapping("/{chatroomId}")
    public ResponseEntity<StatusResponseDto> closeChatRoom(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable long chatroomId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        StatusResponseDto statusResponseDto = chatroomService.closeChatRoom(userDetails.getUserId(),chatroomId);
        if(statusResponseDto.getStatusCode() == 400){
            return ResponseEntity.badRequest().headers(headers).body(statusResponseDto);
        }
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }

}
