package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.users.LoginUserRequestDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import com.sparta.morningworkout.jwtUtil.JwtUtil;
import com.sparta.morningworkout.repository.UserRepository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void signup() {
    }

    @Test
    void login() {
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        LoginUserRequestDto loginUserRequestDto = LoginUserRequestDto.builder()
                .username("username1")
                .password("password1")
                .build();
//
        User user = User.builder()
                .username("username1")
                .password("password1")
                .role(UserRoleEnum.CUSTOMER)
                .build();

        given(userRepository.findByUsername(any())).willReturn(Optional.ofNullable(user));
        given(passwordEncoder.matches(loginUserRequestDto.getPassword(), user.getPassword())).willReturn(true);
        given(jwtUtil.createToken(user.getUsername(), user.getRole())).willReturn(any());

        //when
        String s = userService.login(loginUserRequestDto, mockHttpServletResponse);
//        String token = jwtUtil.createToken(any(), any());

        //then
        Assertions.assertThat(s).isEqualTo("로그인 성공");
//        Assertions.assertThat(mockHttpServletResponse.getHeaderValue("Authorization").toString()).isNotEmpty();

        verify(userRepository).findByUsername(any());
        verify(jwtUtil, times(1)).createToken(any(), any());
    }

    @Test
    void logout() {
    }

    @Test
    void sellerRegist() {
    }
}
