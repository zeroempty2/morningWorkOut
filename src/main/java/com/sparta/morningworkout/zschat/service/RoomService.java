package com.sparta.morningworkout.zschat.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.repository.UserRepository;
import com.sparta.morningworkout.zschat.dto.MessageDto;
import com.sparta.morningworkout.zschat.dto.RoomDto;
import com.sparta.morningworkout.zschat.entity.ChatRoom;
import com.sparta.morningworkout.zschat.entity.Message;
import com.sparta.morningworkout.zschat.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {
	private final MessageService messageService;

	private final ProductRepository productRepository;
	private final UserRepository userRepository;

	private final RoomRepository roomRepository;

	public RoomDto chatting(MessageDto message, Long productId, String username) {
		Product product = productRepository.findById(productId).orElseThrow(
			() -> new IllegalArgumentException("문의 가능한 상품이 존재하지 않습니다.")
		);

		User user = userRepository.findByUsername(username).orElseThrow(
			() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
		);
		Message messages = messageService.save(message, user.getUsername());
		ChatRoom room = roomRepository.findById(message.getChatRoomId()).orElseThrow(
			() -> new IllegalArgumentException("생성되지 않은 방입니다.")
		);
		//
		// ChatRoom rooms = new ChatRoom(message.getChatRoomId(), product);
		// roomRepository.save(rooms);
		// return new RoomDto(rooms);
		room.builder().messages((List<Message>)messages);
		room.builder().product(product);
		room.builder().id(message.getChatRoomId());

		roomRepository.save(room);
		return new RoomDto(room);
	}
}
