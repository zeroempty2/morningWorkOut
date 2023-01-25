package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.dto.chat.ChatResponseDto;
import com.sparta.morningworkout.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Long>{
    @Query("select new com.sparta.morningworkout.dto.chat.ChatResponseDto(c.userId,p.nickname,c.contents) " +
            "from Chat c left join Profile p on c.userId = p.id where  c.chatRoomId = :chatRoomId order by c.id asc")
    List<ChatResponseDto> findALLChatByChatRoom(long chatRoomId);
}
