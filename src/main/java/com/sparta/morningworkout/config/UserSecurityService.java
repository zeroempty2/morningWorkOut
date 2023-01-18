// package com.sparta.morningworkout.config;
//
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;
//
// import org.springframework.http.HttpStatus;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;
// import org.springframework.web.server.ResponseStatusException;
//
// import com.sparta.morningworkout.entity.User;
// import com.sparta.morningworkout.entity.UserRoleEnum;
// import com.sparta.morningworkout.repository.UserRepository;
//
// import lombok.RequiredArgsConstructor;
//
// @Service
// @RequiredArgsConstructor
// public class UserSecurityService implements UserDetailsService {
// 	private final UserRepository userRepository;
// 	@Override
// 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
// 		Optional<User> user = this.userRepository.findByUsername(username);
// 		if (!user.isPresent()) {
// 			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "등록되지 않은 사용자입니다.");
// 		}
//
// 		// Spring User 객체의 비밀번호가 화면으로부터 입력 받은 비밀번호와 일치하는지를 검사하는 로직
// 		User users = user.get();
// 		List<GrantedAuthority> authorities = new ArrayList<>();
// 		if ("admin".equals(username)) {
// 			authorities.add(new SimpleGrantedAuthority(UserRoleEnum.ADMIN.getAuthority()));
// 		} else if ("customer".equals(username)){
// 			authorities.add(new SimpleGrantedAuthority(UserRoleEnum.CUSTOMER.getAuthority()));
// 		} else {
// 			authorities.add(new SimpleGrantedAuthority(UserRoleEnum.SELLER.getAuthority()));
// 		}
// 		return new org.springframework.security.core.userdetails.User(users.getUsername(), users.getPassword(), authorities);
// 	}
// }
