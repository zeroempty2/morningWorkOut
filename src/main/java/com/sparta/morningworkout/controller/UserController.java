package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.users.LoginUserRequestDto;
import com.sparta.morningworkout.dto.users.SellerRegistRequestDto;
import com.sparta.morningworkout.dto.users.SignupDto;
import com.sparta.morningworkout.jwtUtil.JwtUtil;
import com.sparta.morningworkout.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/users")
public class UserController {
    private final UserServiceImpl userServiceimpl;
    private final JwtUtil jwtUtil;

    @PostMapping("/sign")
    public ResponseEntity createUser(@RequestBody @Valid SignupDto sign) {
        userServiceimpl.signup(sign);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
    }

    @GetMapping("/login")
    public ResponseEntity login(@RequestBody LoginUserRequestDto loginUserRequestDto, HttpServletResponse response) {
        String generatedToken = userServiceimpl.login(loginUserRequestDto);
        response.addHeader(jwtUtil.AUTHORIZATION_HEADER, generatedToken);
        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request) {
        userServiceimpl.logout(request);
    }

    @PostMapping("/athorization")
    public ResponseEntity athorization(@RequestBody SellerRegistRequestDto sellerRegistRequestDto, HttpServletRequest request) {
        userServiceimpl.sellerRegist(sellerRegistRequestDto, request);
        return new ResponseEntity<>("등록 요청이 성공했습니다", HttpStatus.OK);
    }
}
