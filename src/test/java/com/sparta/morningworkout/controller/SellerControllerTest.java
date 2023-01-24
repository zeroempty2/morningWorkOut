package com.sparta.morningworkout.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.morningworkout.service.CustomerServiceImpl;
import com.sparta.morningworkout.service.SellerServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
@WebMvcTest(controllers = SellerController.class)
@MockBean(JpaMetamodelMappingContext.class)
@AutoConfigureRestDocs
class SellerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    SellerServiceImpl sellerService;

    @Test
    @DisplayName("커스터머 불러오기")
    void showCustomerList() throws Exception {

        MultiValueMap<String,String> requestParam = new LinkedMultiValueMap<>();
        requestParam.set("page","1");

        mockMvc.perform(MockMvcRequestBuilders.get("/sellers/customers").params(requestParam)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                        .andExpect(status().isOk());
    }

    @Test
    @DisplayName("구매요청 수락하기")
    void acceptBuyRequest() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.post("/sellers/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("판매자기준 - 판매상품보기")
    void showMyProducts() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/sellers/products/list").param("page","1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .with(csrf()))
                .andExpect(status().isOk());

    }

    @Test
    void testShowCustomerList() {
    }

    @Test
    void searchByCustomerName() {
    }

    @Test
    void testAcceptBuyRequest() {
    }

    @Test
    void testShowMyProducts() {
    }

    @Test
    void searchMyProductsByKeyword() {
    }
}
