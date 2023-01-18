// package com.sparta.morningworkout.config;
//
// import com.sparta.morningworkout.securityTest.JwtAuthFilter;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.http.SessionCreationPolicy;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
// import com.sparta.morningworkout.jwtUtil.JwtUtil;
//
// import lombok.RequiredArgsConstructor;
//
// @Configuration
// @EnableWebSecurity
// @RequiredArgsConstructor
// public class SecurityConfig {
//
// 	private final JwtUtil jwtUtil;
//
// 	@Bean
// 	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
// 		http.csrf().disable();
// 		http.authorizeHttpRequests().requestMatchers(
// 				new AntPathRequestMatcher("/**")).permitAll()
// 			.and()
// 			.authorizeHttpRequests().requestMatchers("/admin").hasRole("ROLE_ADMIN")
// 			// 화면 깨진 그림 정상 동작
// 			.and()
// 			.headers()
// 			.addHeaderWriter(new XFrameOptionsHeaderWriter((
// 				XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN)))
// 		;
// 		http.sessionManagement()
// 			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//
// 			.and()
// 			.addFilterBefore(new JwtAuthFilter(jwtUtil), UsernamePasswordAuthenticationFilter.class)
// 		;
// 		return http.build();
// 	}
//
// 	@Bean
// 	PasswordEncoder passwordEncoder() {
// 		return new BCryptPasswordEncoder();
// 	}
// }
