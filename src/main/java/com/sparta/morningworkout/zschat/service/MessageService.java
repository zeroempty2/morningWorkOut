package com.sparta.morningworkout.zschat.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.repository.UserRepository;
import com.sparta.morningworkout.zschat.dto.MessageDto;
import com.sparta.morningworkout.zschat.entity.ChatRoom;
import com.sparta.morningworkout.zschat.entity.Message;
import com.sparta.morningworkout.zschat.repository.MessageRepository;
import com.sparta.morningworkout.zschat.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MessageService {
	private final MessageRepository messageRepository;
	private final RoomRepository roomRepository;
	private final UserRepository userRepository;
	@Transactional
	public void save(MessageDto message, String username) {
		User user = userRepository.findByUsername(username).orElseThrow(
			() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
		);
		ChatRoom room = roomRepository.findById(message.getChatRoomId()).orElseThrow(
			() -> new IllegalArgumentException("방이 존재하지 않습니다.")
		);
		Message messages = new Message(message.getMessage(), LocalDateTime.now(), room, user);
		messageRepository.save(messages);
	}
}