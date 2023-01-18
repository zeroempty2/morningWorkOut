package com.sparta.morningworkout.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.admin.SellerRegistResponseDto;
import com.sparta.morningworkout.dto.admin.UserListResponseDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import com.sparta.morningworkout.service.AdminServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureMockMvc // -> webAppContextSetup(webApplicationContext)
//@AutoConfigureRestDocs // -> apply(documentationConfiguration(restDocumentation))
@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = AdminController.class)
@MockBean(JpaMetamodelMappingContext.class)// When using JUnit5
class AdminControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private AdminServiceImpl adminService;


    @Test
    @DisplayName("고객 리스트 불러오기")
    void showCustomerList() throws Exception {
        User user1 = new User("user1","password1", UserRoleEnum.CUSTOMER);
        User user2 = new User("user2","password1", UserRoleEnum.CUSTOMER);
        List<UserListResponseDto> pageable = new ArrayList<>();
        pageable.add(new UserListResponseDto(user1));
        pageable.add(new UserListResponseDto(user2));
        Page<UserListResponseDto> userListResponseDtoPage = new PageImpl<>(pageable);
        when(adminService.showCustomerList(0)).thenReturn(userListResponseDtoPage);

        mockMvc.perform(get("/admin/customers?page=1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userListResponseDtoPage))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                        .andExpect(status().isOk());
    }
    @Test
    @DisplayName("판매자 리스트 불러오기")
    void showSellerList() throws Exception {
        User user1 = new User("user1", "password1", UserRoleEnum.SELLER);
        User user2 = new User("user2", "password1", UserRoleEnum.SELLER);
        List<UserListResponseDto> pageable = new ArrayList<>();
        pageable.add(new UserListResponseDto(user1));
        pageable.add(new UserListResponseDto(user2));
        Page<UserListResponseDto> userListResponseDtoPage = new PageImpl<>(pageable);
        when(adminService.showCustomerList(0)).thenReturn(userListResponseDtoPage);

        mockMvc.perform(get("/admin/sellers?page=1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(userListResponseDtoPage))
                .accept(MediaType.APPLICATION_JSON)
                .with(csrf()))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("판매자 신청 리스트 불러오기")
    void showSellerRegistList() throws Exception {
        List<SellerRegistResponseDto> sellerRegistResponseDto = new ArrayList<>();
        when(adminService.showSellerRegistList()).thenReturn(sellerRegistResponseDto);

        mockMvc.perform(get("/admin/authorizations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(sellerRegistResponseDto))
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                        .andExpect(status().isOk());
    }
    @Test
    @DisplayName("판매자 신청 수락")
    void acceptSellerRegist() throws Exception {
        Long authorizationRequestId =1L;
//        StatusResponseDto statusResponseDto = mock(StatusResponseDto.class);
//        statusResponseDto = new StatusResponseDto(200, "판매자 승인이 완료되었습니다");
        mockMvc.perform(patch("/admin/authorization/accept/{authorizationRequestId}",authorizationRequestId)
//                        .content(objectMapper.writeValueAsBytes(statusResponseDto))
                        .with(csrf()))
                        .andExpect(status().isOk());
    }
    @Test
    @DisplayName("판매자 권한 삭제")
    void deleteSellerRegist() throws Exception {
        mockMvc.perform(patch("/admin/authorization/delete/1")
                        .with(csrf()))
                        .andExpect(status().isOk());
    }
}
