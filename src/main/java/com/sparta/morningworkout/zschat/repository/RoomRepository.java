package com.sparta.morningworkout.zschat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.zschat.entity.ChatRoom;

public interface RoomRepository extends JpaRepository<ChatRoom,Long> {
    List<ChatRoom> findByUser(User user);

    ChatRoom findByProductIdAndRoomId(Long productId, Long roomId);
}