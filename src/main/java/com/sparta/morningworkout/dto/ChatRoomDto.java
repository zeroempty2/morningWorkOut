package com.sparta.morningworkout.dto;

import com.sparta.morningworkout.entity.chat.ChatRoom;
import lombok.Getter;

@Getter
public class ChatRoomDto {

    private String roomName;
    private String customerName;
    private String sellerName;
    private Long productId;

    public ChatRoomDto(ChatRoom chatRoom) {
        this.roomName = chatRoom.getCustomer().getUsername() + "의 " + chatRoom.getProduct().getProductName() + "구매를 위한 채팅입니다";
        this.customerName = chatRoom.getCustomer().getUsername();
        this.sellerName = chatRoom.getSeller().getUsername();
        this.productId = chatRoom.getProduct().getId();
    }

}
