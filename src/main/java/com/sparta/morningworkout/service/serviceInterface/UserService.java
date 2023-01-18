package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.users.LoginUserRequestDto;
import com.sparta.morningworkout.dto.users.SellerRegistRequestDto;
import com.sparta.morningworkout.dto.users.SignupDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

public interface UserService {
    void signup(SignupDto signupDto);
    String login(LoginUserRequestDto loginUserRequestDto);
    void logout(User user);
    void sellerRegist(SellerRegistRequestDto sellerRegistRequestDto, User user);
}
