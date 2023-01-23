package com.sparta.morningworkout.zschat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.morningworkout.zschat.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Long> {
}
