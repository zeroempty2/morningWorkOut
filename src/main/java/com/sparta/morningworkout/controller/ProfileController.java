package com.sparta.morningworkout.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.sparta.morningworkout.service.ProfileServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {
	private final ProfileServiceImpl profileService;

	@GetMapping("/customer/{id}")
	public ResponseEntity<ShowCustomerProfileResponseDto> showCustomerProfile(@PathVariable Long id) {
		ShowCustomerProfileResponseDto showCustomerProfile = profileService.showCustomerProfile(id);
		return ResponseEntity.status(HttpStatus.OK).body(showCustomerProfile);
	}

	@GetMapping("/seller/{id}")
	public ResponseEntity<ShowSellerProfileResponseDto> showSellerProfile(@PathVariable Long id) {
		ShowSellerProfileResponseDto showSellerProfileList = profileService.showSellerProfile(id);
		return ResponseEntity.status(HttpStatus.OK).body(showSellerProfileList);
	}

	@PutMapping("/customer/{id}")
	public ResponseEntity<String> updateCustomerProfile(@PathVariable long id,
		@RequestBody UpdateCustomerProfileRequestDto request, @AuthenticationPrincipal UserDetails userDetails) {
		profileService.updateCustomerProfile(id, request, userDetails.getUsername());
		return new ResponseEntity<>("프로필 수정이 완료되었습니다.", HttpStatus.OK);
	}

	@PutMapping("/seller/{id}")
	public ResponseEntity<String> updateSellerProfile(@PathVariable long id,
		@RequestBody UpdateSellerProfileRequestDto request) {
		profileService.updateSellerProfile(id, request);
		return new ResponseEntity<>("프로필 수정이 완료되었습니다.", HttpStatus.OK);
	}
}
