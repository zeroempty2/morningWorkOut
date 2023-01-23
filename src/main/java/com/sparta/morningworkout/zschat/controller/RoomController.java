package com.sparta.morningworkout.zschat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.morningworkout.zschat.dto.MessageDto;
import com.sparta.morningworkout.zschat.dto.RoomDto;
import com.sparta.morningworkout.zschat.service.RoomService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoomController {
	private final RoomService roomService;


	@RequestMapping(value = {"/chat/{productId}"})
	public ResponseEntity<RoomDto> chatting(@RequestParam("product") Long productId, @AuthenticationPrincipal
		UserDetails userDetails, MessageDto message) {
		RoomDto room = roomService.chatting(message, productId, userDetails.getUsername());
		return ResponseEntity.status(HttpStatus.OK).body(room);
	}
}

