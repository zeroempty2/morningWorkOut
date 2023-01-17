package com.sparta.morningworkout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class SellerRegistList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long userId;
    private String introcontent;
    private String category;

    public SellerRegistList(long userId, String introcontent, String category) {
        this.userId = userId;
        this.introcontent = introcontent;
        this.category = category;
    }
}
