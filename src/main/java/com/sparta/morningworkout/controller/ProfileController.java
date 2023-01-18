package com.sparta.morningworkout.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.morningworkout.dto.profile.ShowCustomerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.entity.SellerRegist;
import com.sparta.morningworkout.service.ProfileServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
	private final ProfileServiceImpl profileService;

	@GetMapping("/customer/{id}")
	public ResponseEntity showCustomerProfile(@PathVariable Long id) {
		Profile profile = new Profile();
		ShowCustomerProfileResponseDto showCustomerProfile = profileService.showCustomerProfile(id, profile.getNickname());
		return ResponseEntity.status(HttpStatus.OK).body(showCustomerProfile);
	}

	@GetMapping("/seller")
	public ResponseEntity showSellerProfile(@PathVariable Long id) {
		Profile profile = new Profile();
		SellerRegist sellerRegist = new SellerRegist();
		ShowSellerProfileResponseDto showSellerProfileList = profileService.showSellerProfile(id, profile.getNickname(),
			sellerRegist.getInfoContent(), sellerRegist.getCategory());

		return ResponseEntity.status(HttpStatus.OK).body(showSellerProfileList);
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity updateCustomerProfile(@PathVariable long id,
		@RequestBody UpdateCustomerProfileRequestDto request) {
		profileService.updateCustomerProfile(id, request);
		return new ResponseEntity<>("프로필 수정이 완료되었습니다.", HttpStatus.OK);
	}

	@PutMapping("/seller/{id}")
	public ResponseEntity updateSellerProfile(@PathVariable long id,
		@RequestBody UpdateSellerProfileRequestDto request) {
		profileService.updateSellerProfile(id, request);
		return new ResponseEntity<>("프로필 수정이 완료되었습니다.", HttpStatus.OK);
	}
}
