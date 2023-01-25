//package com.sparta.morningworkout.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.morningworkout.service.CustomerServiceImpl;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.http.MediaType;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
//@WebMvcTest(controllers = CustomerController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//class CustomerControllerTest {
//
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    CustomerServiceImpl customerService;
//
//
//    @Test
//    @DisplayName("ShowSellerList_Test")
//    void showSellerList() throws Exception {
//
//        //given
////        User user1 = new User("user1","password1", UserRoleEnum.SELLER);
////        User user2 = new User("user2","password2", UserRoleEnum.SELLER);
////
////        List<SellerListResponseDto> pageable = new ArrayList<>();
////        pageable.add(new SellerListResponseDto(user1));
////        pageable.add(new SellerListResponseDto(user2));
////
////        Page<SellerListResponseDto> sellerListResponseDtoPage = new PageImpl<>(pageable);
////        when(customerService.showSellerList(0)).thenReturn();
//        MultiValueMap<String,String> requestParam = new LinkedMultiValueMap<>();
//        requestParam.set("page","1");
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/customers/sellers").params(requestParam)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .with(csrf()))
//                .andExpect(status().isOk());
//
//
//    }
//
//    @Test
//    @DisplayName("requestBuyProducts")
//    void requestBuyProducts() throws Exception {
//
//
//        mockMvc.perform(post("/customers/products/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .with(csrf()))
//                .andExpect(status().isOk());
//    }
//}
