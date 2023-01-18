package com.sparta.morningworkout.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.morningworkout.dto.profile.ShowCustomerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;
import com.sparta.morningworkout.entity.CategoryEnum;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.entity.SellerRegist;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.repository.SellerRegistRepository;
import com.sparta.morningworkout.service.serviceInterface.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

	private final ProfileRepository profileRepository;
	private final SellerRegistRepository sellerRegistRepository;

	@Override
	public void updateCustomerProfile(long id, UpdateCustomerProfileRequestDto request) {
		Profile profile = profileRepository.findById(id).orElseThrow(

			() -> new RuntimeException("수정하려는 프로필이 없습니다.")
		);
		profile.customerUpdate(request);
	}

	@Override
	public void updateSellerProfile(long id, UpdateSellerProfileRequestDto request) {
		Profile profile = profileRepository.findById(id).orElseThrow(

			() -> new RuntimeException("수정하려는 프로필이 없습니다.")
		);
		profile.sellerUpdate(request);
	}

	@Override
	@Transactional(readOnly = true)
	public ShowCustomerProfileResponseDto showCustomerProfile(long id, String nickname) {
		Profile profile = profileRepository.findByNickname(nickname);
		return new ShowCustomerProfileResponseDto(id, profile);
	}

	@Override
	@Transactional(readOnly = true)
	public ShowSellerProfileResponseDto showSellerProfile(long id, String nickname, String infoContent,
		CategoryEnum category) {
		Profile profile = profileRepository.findByNickname(nickname);
		SellerRegist sellerRegist = sellerRegistRepository.findByInfoContentAndCategory(infoContent, category);
		return new ShowSellerProfileResponseDto(id, profile, sellerRegist);
	}
}
