package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.chat.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

}
