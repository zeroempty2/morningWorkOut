package com.sparta.morningworkout.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.service.serviceInterface.ProfileService;

@ExtendWith(MockitoExtension.class) // 환경 설정 체크 -> 목뭐시기로 사용해서
class ProfileServiceImplTest {

	@Mock // 아래꺼 사용할거임
	ProfileRepository repository;
	@Mock
	ProfileService service;
	// @InjectMocks // mock 주입될
	// ProfileServiceImpl profileService;


	@Test
	void updateCustomerProfile() {
	}

	@Test
	void updateSellerProfile() {
	}

	@Test
	void 고객프로필전체조회() throws Exception {
		// //given
		// Profile profile = mock(Profile.class);
		// ShowCustomerProfileResponseDto response = mock(ShowCustomerProfileResponseDto.class);
		// when(profile.getNickname()).thenReturn("닉네임1");
		// when(repository.findByNickname(profile.getNickname())).thenReturn(profile);
		// //when
		// // when(service.showCustomerProfile()
		// //then
		// assertThat(response.getNickname()).isEqualTo(profile.getNickname());
	}

	@Test
	void showSellerProfile() {
	}
}