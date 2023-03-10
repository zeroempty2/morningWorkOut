//package com.sparta.morningworkout.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.sparta.morningworkout.service.ProfileServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
//import org.springframework.restdocs.RestDocumentationExtension;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ExtendWith({SpringExtension.class, RestDocumentationExtension.class})
//@WebMvcTest(controllers = ProfileController.class)
//@MockBean(JpaMetamodelMappingContext.class)
//@AutoConfigureRestDocs
//class ProfileControllerTest {
//
//    @Autowired
//    MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    ProfileServiceImpl profileService;
//
//    @Test
//    void showCustomerProfile() {
//    }
//
//    @Test
//    void showSellerProfile() {
//    }
//
//    @Test
//    void updateCustomerProfile() {
//    }
//
//    @Test
//    void updateSellerProfile() {
//    }
//}
