package com.sparta.morningworkout.zschat.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.repository.ProfileRepository;
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
	private final ProfileRepository profileRepository;
	@Transactional
	public Message save(MessageDto message, String nickname) {
		Profile profile = profileRepository.findByNickname(nickname);
		ChatRoom room = roomRepository.findById(message.getChatRoomId()).orElseThrow(
			() -> new IllegalArgumentException("방이 존재하지 않습니다.")
		);
		Message messages = new Message(message.getMessage(), LocalDateTime.now(), room, profile);
		messageRepository.save(messages);
		return messages;
	}
}