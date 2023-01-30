package com.sparta.morningworkout.controller;

import com.google.gson.Gson;
import com.sparta.morningworkout.dto.users.SignupDto;
import com.sparta.morningworkout.service.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {


    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;


    private MockMvc mockMvc;//Controller를 테스트하기 위한 객체

    @BeforeEach // 테스트 시작 전 호출하게 되는 메서드
    public void init(){
        //mockMvc객체를 초기화해주는 작업
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    @DisplayName("회원가입 성공")
    void createUser() throws Exception {

        //given
        SignupDto request = SignupDto.builder()
                .username("asd1234")
                .password("asd123445!")
                .admin(false)
                .build();

        given(userService.signup(request)).willReturn("회원가입 성공");

        ResponseEntity responseEntity = ResponseEntity.status(HttpStatus.CREATED).body(userService.signup(request));
        //when
        ResultActions resultActions = mockMvc.perform(
                        MockMvcRequestBuilders.post("/users/sign")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(new Gson().toJson(request))
                );

        //then
        assertThat(responseEntity).isEqualTo(responseEntity);
    }

    @Test
    @DisplayName("회원가입 실패(아이디)")
    void createUser_failed_id() throws Exception {

        //given
        SignupDto request = SignupDto.builder()
                .username("as")
                .password("asd123445!")
                .admin(false)
                .build();



        //when
        ResultActions resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/users/sign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new Gson().toJson(request))
        );

        //then
        resultActions.andExpect(status().isBadRequest());
    }

    @Test
    void login() {

        //given


        //when

        //then
    }

    @Test
    void athorization() {
    }
}