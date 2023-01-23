package com.sparta.morningworkout.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@NoArgsConstructor
@Getter
@SuperBuilder
public class CustomerRequestList extends TimeStamped{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private long userId;

    @Column(nullable = false)
    private long sellerId; // 사실 여기도 userId필드지만, 판매자의 userI가 들어가야 하는 분

    @Column(nullable = false)
    private long productId;

    @Column
    private boolean isAccepted;

    public CustomerRequestList(long userId, long sellerId, long productId) {
        this.userId = userId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.isAccepted = false;
    }

    public void acceptBySeller(){
        this.isAccepted = true;
    }
}
