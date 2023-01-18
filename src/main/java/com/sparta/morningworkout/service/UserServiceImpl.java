package com.sparta.morningworkout.service;



import com.sparta.morningworkout.dto.users.LoginUserRequestDto;
import com.sparta.morningworkout.dto.users.SellerRegistRequestDto;
import com.sparta.morningworkout.dto.users.SignupDto;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.entity.SellerRegist;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import com.sparta.morningworkout.jwtUtil.JwtUtil;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.repository.SellerRegistRepository;
import com.sparta.morningworkout.repository.UserRepository;
import com.sparta.morningworkout.service.serviceInterface.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SellerRegistRepository sellerRegistRepository;
    private final JwtUtil jwtUtil;
    private final ProfileRepository profileRepository;
    private static final String ADMIN_TOKEN = "asdasd";

    @Override
    @Transactional
    public void signup(SignupDto sign) {
        Optional<User> users = userRepository.findByUsername(sign.getUsername());
        if (users.isPresent()) { throw new IllegalArgumentException("유저가 존재합니다"); }

        UserRoleEnum role = UserRoleEnum.CUSTOMER;
        if (sign.isAdmin()) {
            if (!sign.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("운영자 암호가 틀려 등록이 불가");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(sign.getUsername(), sign.getPassword(),role);
        userRepository.save(user);
        Profile profile = new Profile(user.getId(),sign.getNickname());
        profileRepository.save(profile);
    }

    @Override
    public String login(LoginUserRequestDto loginUserRequestDto) {
        User user = userRepository.findByUsername(loginUserRequestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다"));
        if (!user.getPassword().equals(loginUserRequestDto.getPassword()))
        { throw new IllegalArgumentException("비밀번호 불일치"); }
        return jwtUtil.createToken(user.getUsername(), user.getRole());
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
    public void sellerRegist(SellerRegistRequestDto sellerRegistRequestDto, HttpServletRequest request) {
        String token = jwtUtil.resolveToken(request);
        Claims claims;
        if (token != null) {
            if (jwtUtil.validateToken(token)) {
                claims = jwtUtil.getUserInfoFromToken(token);
                SellerRegist sellerRegist = new SellerRegist(Long.parseLong(claims.getId()),claims.getSubject(),
                        sellerRegistRequestDto.getInfocontent(),sellerRegistRequestDto.getCategoryEnum());
                sellerRegistRepository.save(sellerRegist);
            } else { throw  new IllegalArgumentException("유효하지 않은 토큰"); }
        } else { throw new IllegalArgumentException("토큰값이 잘못됨"); }
    }
}
