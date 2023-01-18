package com.sparta.morningworkout.dto.profile;

import com.sparta.morningworkout.entity.CategoryEnum;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.entity.SellerRegist;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ShowSellerProfileResponseDto {
	private long id;
    private String nickname;
//    private String image;
    private String infoContent;
    private CategoryEnum category;

	public ShowSellerProfileResponseDto(long id, Profile profiles, SellerRegist sellerRegist) {
		this.id = id;
		this.nickname = profiles.getNickname();
		this.infoContent = sellerRegist.getInfoContent();
		this.category = sellerRegist.getCategory();
	}{
	}
}