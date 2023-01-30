package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.users.LoginUserRequestDto;
import com.sparta.morningworkout.dto.users.SignupDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import com.sparta.morningworkout.jwtUtil.JwtUtil;
import com.sparta.morningworkout.repository.PointRepository;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)// junit과 mockito를 연결
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private PointRepository pointRepository;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private UserServiceImpl userService;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원 가입성공")
    void signup() {

        //given
        SignupDto signupDto = SignupDto.builder()
                .username("asd1234")
                .password("asd12345!")
                .nickname("일반유저")
                .admin(false)
                .build();

        //가짜 객체의 리턴값을 만들어주는 작업
        when(userRepository.findByUsername(any(String.class)))
                .thenReturn(Optional.empty());


        //when
        String msg = userService.signup(signupDto);

        //then
        assertThat(msg).isEqualTo("회원가입 성공");

    }

    @Test
    @DisplayName("회원 가입실패")
    void signupFail() {

        //given
        SignupDto signupDto = SignupDto.builder()
                .username("asd1234")
                .password("asd12345!")
                .nickname("일반유저")
                .admin(false)
                .build();

        //가짜 객체의 리턴값을 만들어주는 작업
        when(userRepository.findByUsername(any(String.class)))
                .thenReturn(Optional.ofNullable(User.builder()
                        .username("asd1234")
                        .password("asd12345!")
                        .role(UserRoleEnum.CUSTOMER)
                        .build())
                );


        //when
//        String msg = userService.signup(signupDto);


        //then
        assertThatThrownBy(
                () -> userService.signup(signupDto)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유저가 존재합니다");

    }

    @Test
    @DisplayName("로그인")
    void login() {

        //given

        LoginUserRequestDto loginUserRequestDto = LoginUserRequestDto.builder()
                .username("asd1234")
                .password("asd12345!")
                .build();


        User user = User.builder()
                .username("asd1234")
                .password(passwordEncoder.encode("asd12345!"))
                .role(UserRoleEnum.CUSTOMER)
                .build();

        //HttpServlet을 위한 객체
        MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();

        when(userRepository.findByUsername(any(String.class)))
                .thenReturn(Optional.of(user));
        //when

        String msg = userService.login(loginUserRequestDto,mockHttpServletResponse);
        String token = jwtUtil.createToken(any(),any());

        //then
        assertThat(msg).isEqualTo("로그인 성공");
       // assertThat(mockHttpServletResponse.getHeaderValue("Authorization").toString()).isNotEmpty();

        //어떤 함수가 몇번 불려졌냐? 행위검증
        //verify(userRepository,times(1)).saveAndFlush(any(User.class));
    }
}