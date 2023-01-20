package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    ChatRoom findByRoomId(Long roomId);

    ChatRoom findByCustomerAndProduct(Long customerId, Long productId);

    List<ChatRoom> findAllByCustomer(Long customerId);

    List<ChatRoom> findAllBySeller(Long sellerId);

    List<ChatRoom> findAllByProduct(Long ProductId);
}
