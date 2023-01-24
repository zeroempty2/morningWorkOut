package com.sparta.morningworkout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@NoArgsConstructor
@SuperBuilder
public class ChatRoom extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private long sendUserId;
    @Column(nullable = false)
    private long receiveUserId;
    @Column(nullable = false)
    private boolean isClosed;

    public void closeChatRoom(){
        this.isClosed = true;
    }
}
