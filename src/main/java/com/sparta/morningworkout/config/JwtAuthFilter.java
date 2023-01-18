// package com.sparta.morningworkout.config;
//
// import java.io.IOException;
//
// import org.springframework.security.core.Authentication;
// import org.springframework.http.HttpStatus;
// import org.springframework.security.core.context.SecurityContext;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.web.filter.OncePerRequestFilter;
// import org.springframework.web.server.ResponseStatusException;
//
// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.sparta.morningworkout.dto.StatusResponseDto;
// import com.sparta.morningworkout.jwtUtil.JwtUtil;
//
// import io.jsonwebtoken.Claims;
// import jakarta.servlet.FilterChain;
// import jakarta.servlet.ServletException;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
// import lombok.RequiredArgsConstructor;
// import lombok.extern.slf4j.Slf4j;
//
// @Slf4j
// @RequiredArgsConstructor
// public class JwtAuthFilter extends OncePerRequestFilter {
// 	public  final JwtUtil jwtUtil;
//
// 	@Override
// 	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
// 		FilterChain filterChain) throws ServletException, IOException {
// 		String token = jwtUtil.resolveToken(request);
//
// 		if (token != null) {
// 			if (!jwtUtil.validateToken(token)) {
// 				throw new ResponseStatusException(HttpStatus.BAD_REQUEST ,"토큰이 유효하지 않습니다.");
// 			}
// 			Claims claims = jwtUtil.getUserInfoFromToken(token);
// 		}
//
// 		filterChain.doFilter(request, response);
// 	}
//
// 	public void setAuthentication(String username) {
// 		// 꺼내쓰기
// 		SecurityContext context = SecurityContextHolder.createEmptyContext();
// 		// 인증
// 		Authentication authentication = jwtUtil.createAuthentication(username);
// 		context.setAuthentication(authentication);
// 		SecurityContextHolder.setContext(context);
// 	}
//
// 	public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
// 		// 토큰 오류시 클라이언트에게 Exception 처리 값을 알려주는 함수이다.
// 		response.setStatus(statusCode);
// 		response.setContentType("application/json");
// 		try {
// 			String json = new ObjectMapper().writeValueAsString(new StatusResponseDto(statusCode, msg));
// 			response.getWriter().write(json);
// 		} catch (Exception e) {
// 			log.error(e.getMessage());
// 		}
// 	}
// }
