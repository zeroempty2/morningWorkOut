package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.LoginUserRequest;
import com.sparta.morningworkout.dto.SellerRegistRequest;
import com.sparta.morningworkout.dto.Signup;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    void signup(Signup signup);
    String login(LoginUserRequest loginUserRequest);
    void logout(HttpServletRequest request);
    void sellerRegist(SellerRegistRequest sellerRegistRequest, HttpServletRequest request);
}
