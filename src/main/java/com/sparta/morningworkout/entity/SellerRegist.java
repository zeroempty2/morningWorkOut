package com.sparta.morningworkout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class SellerRegist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long userId;
    @Column(nullable = false)
    private String username;

    public boolean isAccept() {
        return false;
    }
    private String introcontent;
    private String category;

    public SellerRegist(long userId, String introcontent, String category) {
        this.userId = userId;
        this.introcontent = introcontent;
        this.category = category;
    }
}
