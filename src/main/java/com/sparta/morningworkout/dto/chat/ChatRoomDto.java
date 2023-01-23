// package com.sparta.morningworkout.dto.chat;
//
// import com.sparta.morningworkout.entity.Product;
// import com.sparta.morningworkout.entity.User;
// import com.sparta.morningworkout.entity.chat.ChatRoom;
// import com.sparta.morningworkout.entity.chat.Message;
// import lombok.Builder;
// import lombok.Getter;
// import lombok.NoArgsConstructor;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.stream.Collectors;
//
// @Getter
// @NoArgsConstructor
// public class ChatRoomDto {
//
//     private Long roomId;
//     private User sellerId;
//     private User customerId;
//     private List<MessageDto> messages;
//
//     private final String greeting = "문의 주셔서 감사합니다. 빠른 답변 드리겠습니다.";
//
//     public ChatRoomDto(ChatRoom chatRoom) {
//         this.roomId = chatRoom.getRoomId();
//         List<MessageDto> messageList = chatRoom.getMessages().stream().map(MessageDto::new).collect(Collectors.toList());
//     }
// }
