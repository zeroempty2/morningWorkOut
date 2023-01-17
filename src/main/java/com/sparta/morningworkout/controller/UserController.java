package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.LoginUserRequest;
import com.sparta.morningworkout.dto.ResponseDto;
import com.sparta.morningworkout.dto.SellerRegistRequest;
import com.sparta.morningworkout.dto.Signup;
import com.sparta.morningworkout.jwtUtil.JwtUtil;
import com.sparta.morningworkout.service.UserService;
import com.sparta.morningworkout.service.UserServiceImpl;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/users")
public class UserController {
    private final UserServiceImpl userServiceimpl;
    private final JwtUtil jwtUtil;

    @PostMapping("/sign")
    public ResponseDto createUser(@RequestBody @Valid Signup sign) {
        userServiceimpl.signup(sign);
        return new ResponseDto(201L, "회원가입이 완료되었습니다");
    }

    @GetMapping("/login")
    public ResponseDto login(@RequestBody LoginUserRequest loginUserRequest, HttpServletResponse response) {
        String generatedToken = userServiceimpl.login(loginUserRequest);
        response.addHeader(jwtUtil.AUTHORIZATION_HEADER, generatedToken);
        return new ResponseDto(200L, "로그인 성공");
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        userServiceimpl.logout(request);
    }

    @PostMapping("/athorization")
    public ResponseDto athorization(@RequestBody SellerRegistRequest sellerRegistRequest, HttpServletRequest request) {
        userServiceimpl.sellerRegist(sellerRegistRequest, request);
        return new ResponseDto(200L, "등록 요청이 성공했습니다");
    }
}
