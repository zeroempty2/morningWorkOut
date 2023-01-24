package com.sparta.morningworkout.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.morningworkout.dto.users.LoginUserRequestDto;
import com.sparta.morningworkout.dto.users.SignupDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.UserServiceImpl;
import com.sparta.morningworkout.service.serviceInterface.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.sparta.morningworkout.controller.ApiDocumentUtils.getDocumentRequest;
import static com.sparta.morningworkout.controller.ApiDocumentUtils.getDocumentResponsee;
import static org.hamcrest.core.Is.isA;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = UserController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    UserServiceImpl userService;

    @Test
    @WithMockUser(username = "username1", roles ="CUSTOMER")
    void createUser() throws Exception {

        SignupDto requestDto = SignupDto.builder()
                .username("username1")
                .password("password11**")
                .admin(false)
                .nickname("nickname1")
                .adminToken("adminToken")
                .build();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users/sign")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isCreated());

//        resultActions .andDo(document("UserController",
//                getDocumentRequest(),
//                getDocumentResponsee(),
////
//                requestFields(
//                        fieldWithPath("username").type(JsonFieldType.STRING).description("회원_이름"),
//                        fieldWithPath("password").type(JsonFieldType.STRING).description("회원_비밀번호"),
//                        fieldWithPath("admin").type(JsonFieldType.BOOLEAN).description("회원_자격"),
//                        fieldWithPath("adminToken").type(JsonFieldType.STRING).description("회원 비밀번호"),
//                        fieldWithPath("nickname").type(JsonFieldType.STRING).description("회원 닉네임")
//                ),
//                responseFields(
//                        fieldWithPath("httpStatusCode").type(JsonFieldType.STRING).description("결과코드"),
//                        fieldWithPath("msg").type(JsonFieldType.NULL).description("결과메시지")
//                )
//        ));
    }

    @Test
    @WithMockUser(username = "username1", roles ="CUSTOMER")
    void login() throws Exception {

        LoginUserRequestDto requestDto = LoginUserRequestDto.builder()
                .username("username1")
                .password("password11**")
                .build();

        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(requestDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
//                .andExpect(content().string(containsString("로그인 성공")));
    }

    @Test
//    @WithMockUser(username = "username1", roles ="CUSTOMER")
    @WithUserDetails(value = "userDetails", userDetailsServiceBeanName = "userDetailsServiceImpl")
    void logout() throws Exception {
        User user = User.builder().username("userDetails").password("password11!!").build();

//        UserDetailsImpl userDetails = mock(UserDetailsImpl.class);
//        given(userDetails.getUser()).willReturn((User) isA(User.class));
        ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.post("/users/logout")
                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(requestDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(content().string("로그아웃 성공"));
    }

    @Test
    void athorization() {
    }
}
