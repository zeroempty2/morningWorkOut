package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.chat.ChatRequestDto;
import com.sparta.morningworkout.dto.chat.ChatResponseDto;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.ChatServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatServiceImpl chatService;
    @PostMapping
    public ResponseEntity<StatusResponseDto> sendChat(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody ChatRequestDto chatRequestDto) {
        StatusResponseDto statusResponseDto = chatService.sendChat(chatRequestDto, userDetails.getUserId());
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }
    @GetMapping("/{chatRoomId}")
    public List<ChatResponseDto> loadChatRoom(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable long chatRoomId) {
        return chatService.loadChat(userDetails.getUserId(), chatRoomId);
    }

}
