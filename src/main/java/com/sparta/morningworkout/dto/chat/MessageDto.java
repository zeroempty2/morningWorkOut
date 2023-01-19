package com.sparta.morningworkout.dto.chat;

import com.sparta.morningworkout.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {

    private User Sender;
    private String content;
    private LocalDateTime time;

}
