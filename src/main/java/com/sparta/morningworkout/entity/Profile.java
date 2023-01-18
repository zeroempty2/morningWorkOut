package com.sparta.morningworkout.entity;

import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Profile {
    @Id
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

    public Profile(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
        this.infoContent = null;
        this.category = null;
    }
    public void authorizationProfileUpdate(SellerRegist sellerRegist) {
        this.infoContent = sellerRegist.getInfoContent();
        this.category = sellerRegist.getCategory();
    }

    public Profile(Long id, String nickname, SellerRegist regist) {
        this.id = id;
        this.nickname = nickname;
        this.infoContent = regist.getInfoContent();
        this.category = regist.getCategory();
    }


    // public Profile(User user, CreateCustomerRequestDto request) {
    //     this.id = getId();
    //     this.nickname = request.getNickname();
    // }
    //
    // public Profile(User user, CreateSellerRequestDto request) {
    //     this.id = getId();
    //     this.nickname = request.getNickname();
    //     this.infoContent = request.getInfoContent();
    //     this.category = getCategory();
    // }

    public void customerUpdate(UpdateCustomerProfileRequestDto request) {
        this.nickname = request.getNickname();
    }

    public void sellerUpdate(UpdateSellerProfileRequestDto request) {
        this.nickname = request.getNickname();
        this.infoContent = request.getInfoContent();
        this.category = request.getCategory();
    }
}
