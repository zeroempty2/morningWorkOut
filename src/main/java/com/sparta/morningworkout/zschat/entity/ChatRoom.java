package com.sparta.morningworkout.zschat.entity;

import java.util.ArrayList;
import java.util.List;

import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.zschat.dto.MessageDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name =  "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.REMOVE)
    private List<Message> messages = new ArrayList<>();

    public ChatRoom(Long id, Product product, User user) {
        this.id = id;
        this.user = user;
        this.product = product;
    }

    public ChatRoom(Long chatRoomId, Product product, MessageDto message) {
    }
}
