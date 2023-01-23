package com.sparta.morningworkout.zschat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.morningworkout.zschat.entity.ChatRoom;

public interface RoomRepository extends JpaRepository<ChatRoom,Long> {
}