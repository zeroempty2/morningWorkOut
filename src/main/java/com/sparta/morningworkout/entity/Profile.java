package com.sparta.morningworkout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String nickname;
//    @Column(nullable = false)
//    private String image;
    @Column
    private String infoContent;
    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;
}
