package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.LoginUserRequest;
import com.sparta.morningworkout.dto.SellerRegistRequest;
import com.sparta.morningworkout.dto.Signup;
import com.sparta.morningworkout.entity.SellerRegistList;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import com.sparta.morningworkout.jwtUtil.JwtUtil;
import com.sparta.morningworkout.repository.SellerRegistRepository;
import com.sparta.morningworkout.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final SellerRegistRepository sellerRegistRepository;
    private final JwtUtil jwtUtil;
    private static final String ADMIN_TOKEN = "asdasd";

    @Override
    @Transactional
    public void signup(Signup sign) {
        Optional<User> users = userRepository.findByUsername(sign.getUsername());
        if (users.isPresent()) { throw new IllegalArgumentException("유저가 존재합니다"); }

        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        if (sign.getRole().equals(UserRoleEnum.ADMIN)) {
            if (!sign.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("운영자 암호가 틀려 등록이 불가");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(sign.getUsername(), sign.getPassword(), sign.getRole(), sign.getNickname());
        userRepository.save(user);
    }

    @Override
    public String login(LoginUserRequest loginUserRequest) {
        User user = userRepository.findByUsername(loginUserRequest.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));
        if (!user.getPassword().equals(loginUserRequest.getPassword()))
        { throw new IllegalArgumentException("비밀번호 불일치"); }
        String generatedToken = jwtUtil.createToken(user.getUsername(), user.getRole());
        return generatedToken;
    }

    @Override
    public void logout(HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                String username = claims.getSubject();
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됨"); }

        Long expiration = jwtUtil.getExpiration(token);
        System.out.println(expiration);
    }

    @Override
    public void sellerRegist(SellerRegistRequest sellerRegistRequest, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                SellerRegistList seller = new SellerRegistList(Long.valueOf(claims.getId()),
                        sellerRegistRequest.getIntrocontent(), sellerRegistRequest.getCategory());
                sellerRegistRepository.save(seller);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됨"); }
    }
}
