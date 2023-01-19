package com.sparta.morningworkout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Point {

    @Id
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private int Point;
    private String adminname;

    public Point(String username, int point, String adminname) {
        this.username = username;
        Point = point;
        this.adminname = null;
    }
}
