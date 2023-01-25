package com.sparta.morningworkout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@NoArgsConstructor
@SuperBuilder
public class Point extends TimeStamped{

    @Id
    @Column(nullable = false)
    private long userId;
    @Column(nullable = false)
    private int point;


    public Point(Long userId, int point) {
        this.userId = userId;
        this.point = point;
    }
    public void plusPoint(int point){
        this.point += point;
    }
    public void minusPoint(int point){
        this.point -= point;
    }
}
