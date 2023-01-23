package com.sparta.morningworkout.entity.chat;


import com.sparta.morningworkout.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;

//@Entity
@DynamicInsert // insert 구문 생성 시 null이 아닌 컬럼만 포함
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    public enum MessageType{
        ENTER, TALK
    }

    private MessageType type;
    private Long roomId;
    private String sender;
    private String message;

}
