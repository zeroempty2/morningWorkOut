package com.sparta.morningworkout.entity;

import com.sparta.morningworkout.dto.UpdateCustomerProfileRequest;
import com.sparta.morningworkout.dto.UpdateSellerProfileRequest;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
    @OneToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Profile(String nickname, String infoContent, CategoryEnum category, User user) {
        this.nickname = nickname;
        this.infoContent = infoContent;
        this.category = category;
        this.user = user;
    }

    public void customerUpdate(UpdateCustomerProfileRequest request) {
        this.nickname = request.getNickname();
    }

    public void sellerUpdate(UpdateSellerProfileRequest request) {
        this.nickname = request.getNickname();
        this.infoContent = request.getInfoContent();
        this.category = request.getCategory();
    }
}
