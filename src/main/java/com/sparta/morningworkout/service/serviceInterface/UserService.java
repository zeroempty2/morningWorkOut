package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.users.LoginUserRequestDto;
import com.sparta.morningworkout.dto.users.SellerRegistRequestDto;
import com.sparta.morningworkout.dto.users.SignupDto;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    void signup(SignupDto signupDto);
    String login(LoginUserRequestDto loginUserRequestDto);
    void logout(HttpServletRequest request);
    void sellerRegist(SellerRegistRequestDto sellerRegistRequestDto, HttpServletRequest request);
}
