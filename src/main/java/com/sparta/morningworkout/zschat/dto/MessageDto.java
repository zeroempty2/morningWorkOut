package com.sparta.morningworkout.zschat.dto;

import com.sparta.morningworkout.zschat.entity.Message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {
    private Long chatRoomId;
    private String receiver;
    private String sender;
    private String message;

	public MessageDto(Message message) {
		this.chatRoomId = message.getChatRoom().getId();
		this.sender = message.getWriter().getNickname();
		this.message = message.getMessage();
	}
}
