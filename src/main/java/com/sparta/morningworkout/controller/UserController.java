package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.users.LoginUserRequestDto;
import com.sparta.morningworkout.dto.users.SellerRegistRequestDto;
import com.sparta.morningworkout.dto.users.SignupDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.jwtUtil.JwtUtil;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.UserServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/users")
public class UserController {
    private final UserServiceImpl userServiceimpl;
    @PostMapping("/sign")
    public ResponseEntity createUser(@RequestBody @Valid SignupDto sign) {
        userServiceimpl.signup(sign);
        return new ResponseEntity<>("회원가입 성공", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginUserRequestDto loginUserRequestDto, HttpServletResponse response) {
        String generatedToken = userServiceimpl.login(loginUserRequestDto);
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, generatedToken);
        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity logout(@AuthenticationPrincipal UserDetailsImpl userDetails, HttpServletResponse response) {
        User user = userDetails.getUser();
        response.setHeader(JwtUtil.AUTHORIZATION_HEADER, null);
        userServiceimpl.logout(user);
        return new ResponseEntity<>("로그아웃 성공", HttpStatus.OK);
    }

    @PostMapping("/athorization")
    public ResponseEntity athorization(@RequestBody SellerRegistRequestDto sellerRegistRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        userServiceimpl.sellerRegist(sellerRegistRequestDto, user);
        return new ResponseEntity<>("등록 요청이 성공했습니다", HttpStatus.OK);
    }
}
