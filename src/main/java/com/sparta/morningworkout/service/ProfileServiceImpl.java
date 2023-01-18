package com.sparta.morningworkout.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.morningworkout.dto.profile.ShowCustomerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.repository.SellerRegistRepository;
import com.sparta.morningworkout.repository.UserRepository;
import com.sparta.morningworkout.service.serviceInterface.ProfileService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

	private final ProfileRepository profileRepository;
	private final SellerRegistRepository sellerRegistRepository;
	private final UserRepository userRepository;

	@Override
	public void updateCustomerProfile(long id, UpdateCustomerProfileRequestDto request, String username) {
		Profile profile = profileRepository.findById(id).orElseThrow(
			() -> new RuntimeException("수정할 프로필이 없습니다.")
		);
		User user = userRepository.findByUsername(username).orElseThrow(
			() -> new IllegalArgumentException("사용자가 존재하지 않습니다.")
		);
		profile.customerUpdate(request);
		this.profileRepository.save(profile);
	}

	@Override
	public void updateSellerProfile(long id, UpdateSellerProfileRequestDto request) {
		Profile profile = profileRepository.findById(id).orElseThrow(
			() -> new RuntimeException("수정할 프로필이 없습니다.")
		);
		profile.sellerUpdate(request);
		this.profileRepository.save(profile);
	}

	@Override
	@Transactional(readOnly = true)
	public ShowCustomerProfileResponseDto showCustomerProfile(long id) {
		Profile profile = profileRepository.findById(id).orElseThrow(
			() -> new RuntimeException("조회할 프로필이 없습니다.")
		);
		return new ShowCustomerProfileResponseDto(profile);
	}

	@Override
	@Transactional(readOnly = true)
	public ShowSellerProfileResponseDto showSellerProfile(long id) {
		Profile profile = profileRepository.findById(id).orElseThrow(
			() -> new RuntimeException("조회할 프로필이 없습니다.")
		);
		return new ShowSellerProfileResponseDto(profile);
	}
}
