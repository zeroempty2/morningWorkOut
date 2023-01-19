package com.sparta.morningworkout.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sparta.morningworkout.repository.ProfileRepository;

@ExtendWith(MockitoExtension.class) // 환경 설정 체크 -> 목뭐시기로 사용해서
class ProfileServiceImplTest {

	@Mock // 아래꺼 사용할거임
	ProfileRepository repository;

	@InjectMocks // mock 주입될
	ProfileServiceImpl profileService;


	@Test
	void updateCustomerProfile() {
	}

	@Test
	void updateSellerProfile() {
	}

	@Test
	void 고객프로필전체조회() {
	}

	@Test
	void showSellerProfile() {
	}
}