package com.sparta.morningworkout.entity;

import java.util.Objects;

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

	public void customerUpdate(UpdateCustomerProfileRequestDto request) {
		this.nickname = request.getNickname();
	}

	public void sellerUpdate(UpdateSellerProfileRequestDto request) {
		this.nickname = request.getNickname();
		this.infoContent = request.getInfoContent();
		this.category = request.getCategory();
	}

	public boolean checkAuthorization(User user) {
		return Objects.equals(id, user.getId());
	}
}
