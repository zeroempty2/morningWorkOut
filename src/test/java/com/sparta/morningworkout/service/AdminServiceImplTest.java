package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.entity.SellerRegist;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.repository.PointRepository;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.repository.SellerRegistRepository;
import com.sparta.morningworkout.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    SellerRegistRepository sellerRegistRepository;

    @Mock
    ProfileRepository profileRepository;

    @Mock
    PointRepository pointRepository;

    @InjectMocks
    AdminServiceImpl adminService;

    @Test
    void acceptSellerRegist() {

        //given
        User user = mock(User.class);
        SellerRegist sellerRegist = mock(SellerRegist.class);
        Profile profile = mock(Profile.class);
        given(userRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(user));
        given(sellerRegistRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(sellerRegist));
        given(profileRepository.findById(any(Long.class))).willReturn(Optional.ofNullable(profile));
        StatusResponseDto statusResponseDto1 = new StatusResponseDto(200, "판매자 승인이 완료되었습니다");

        //when
        StatusResponseDto statusResponseDto = adminService.acceptSellerRegist(any(Long.class));
        verify(user).changeSeller();
        verify(profile).authorizationProfileUpdate(sellerRegist);

        //then
//        verify(sellerRegistRepository).deleteById(any(Long.class));
        Assertions.assertThat(statusResponseDto.getMessage()).isEqualTo(statusResponseDto1.getMessage());
        Assertions.assertThat(statusResponseDto.getStatusCode()).isEqualTo(statusResponseDto1.getStatusCode());

    }
}
