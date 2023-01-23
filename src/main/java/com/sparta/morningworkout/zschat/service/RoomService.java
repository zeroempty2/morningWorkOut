package com.sparta.morningworkout.zschat.service;

import org.springframework.stereotype.Service;

import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.repository.UserRepository;
import com.sparta.morningworkout.zschat.dto.MessageDto;
import com.sparta.morningworkout.zschat.dto.RoomDto;
import com.sparta.morningworkout.zschat.entity.ChatRoom;
import com.sparta.morningworkout.zschat.repository.MessageRepository;
import com.sparta.morningworkout.zschat.repository.RoomRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoomService {
	private final RoomRepository roomRepository;
	private final MessageRepository messageRepository;
	private final ProductRepository productRepository;
	private final UserRepository userRepository;


	public RoomDto chatting(Long productId, String username) {
		Product product = productRepository.findById(productId).orElseThrow(
			() -> new IllegalArgumentException("문의 가능한 상품이 존재하지 않습니다.")
		);

		User user = userRepository.findByUsername(username).orElseThrow(
			() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
		);

		RoomDto roomDto = new RoomDto();

		if (user == null) {
			roomDto.builder().senderNick("")
			roo.setSenderId(0L);
		} else {
			chatRoomDetailDto.setSenderName(userByEmailMethod.getUsername());
			chatRoomDetailDto.setSenderId(userByEmailMethod.getId());
		}

		}
	}
}
