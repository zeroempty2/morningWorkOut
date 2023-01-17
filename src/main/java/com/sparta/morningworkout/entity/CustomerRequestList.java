package com.sparta.morningworkout.entity;


import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CustomerRequestList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long sellerId; // 사실 여기도 userId필드지만, 판매자의 userI가 들어가야 하는 분

    @Column(nullable = false)
    private long productId;

    public CustomerRequestList(long userId, long sellerId, long productId) {
        this.userId = userId;
        this.sellerId = sellerId;
        this.productId = productId;
    }
}
