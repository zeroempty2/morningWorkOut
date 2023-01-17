package com.sparta.morningworkout.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import com.sparta.morningworkout.service.ProfileServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/profile")
public class ProfileController {

	private final ProfileServiceImpl profileService;

	@GetMapping("/customer")
	public ResponseEntity showCustomerProfile(String nickname, User user) {
		ShowCustomerProfileResponseDto showCustomerProfile = new ShowCustomerProfileResponseDto();
		if (user.getRole().equals(UserRoleEnum.CUSTOMER)) {
			showCustomerProfile = profileService.showCustomerProfile(nickname);
		}
		return ResponseEntity.status(HttpStatus.OK).body(showCustomerProfile);
	}

	@GetMapping("/seller")
	public ResponseEntity showSellerProfile(User user) {
		List<ShowSellerProfileResponseDto> showSellerProfileList  = new ArrayList<>();
		if (user.getRole().equals(UserRoleEnum.SELLER)) {
			showSellerProfileList = profileService.showSellerProfile();
		}
		return ResponseEntity.status(HttpStatus.OK).body(showSellerProfileList);
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity updateCustomerProfile(@PathVariable long id, @RequestBody UpdateCustomerProfileRequestDto request) {
		profileService.updateCustomerProfile(id, request);
		return new ResponseEntity<>("프로필 수정이 완료되었습니다.", HttpStatus.OK);
	}

	@PutMapping("/seller/{id}")
	public ResponseEntity updateSellerProfile(@PathVariable long id, @RequestBody UpdateSellerProfileRequestDto request) {
		profileService.updateSellerProfile(id, request);
		return new ResponseEntity<>("프로필 수정이 완료되었습니다.", HttpStatus.OK);
	}
}
