package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.admin.SearchNicknameResponseDto;
import com.sparta.morningworkout.dto.admin.SellerRegistResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.admin.UserListResponseDto;
import com.sparta.morningworkout.service.AdminServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final AdminServiceImpl adminService;

    @GetMapping("/customers")
    public ResponseEntity<Page<UserListResponseDto>> showCustomerList(
            @RequestParam int page,
            @RequestParam int size) {
        Page<UserListResponseDto> responseDto = adminService.showCustomerList(page - 1, size);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    @GetMapping("/sellers")
    public ResponseEntity<Page<UserListResponseDto>> showSellerList(
            @RequestParam int page,
            @RequestParam int size) {
        Page<UserListResponseDto> responseDto = adminService.showSellerList(page - 1, size);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
    @GetMapping("/customers/search/nickname")
    public ResponseEntity<Page<SearchNicknameResponseDto>> showCustomerListBySearchingNickname(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(value = "keyword") String keyword
    ) {
        Page<SearchNicknameResponseDto> responseDto = adminService.showCustomerListBySearchingNickname(page - 1, size, keyword);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
    @GetMapping("/sellers/search/nickname")
    public ResponseEntity<Page<SearchNicknameResponseDto>> showSellerListBySearchingNickname(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(value = "keyword") String keyword
    ) {
        Page<SearchNicknameResponseDto> responseDto = adminService.showSellerListBySearchingNickname(page - 1, size, keyword);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    @GetMapping("/authorizations")
    public ResponseEntity<List<SellerRegistResponseDto>> showSellerRegistList() {
        List<SellerRegistResponseDto> responseDto = adminService.showSellerRegistList();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    @PatchMapping("/authorization/accept/{authorizationRequestId}")
    public ResponseEntity<StatusResponseDto> acceptSellerRegist(@PathVariable Long authorizationRequestId) {
        StatusResponseDto statusResponseDto = adminService.acceptSellerRegist(authorizationRequestId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }

    @PatchMapping("/authorization/delete/{authorizationRequestId}")
    public ResponseEntity<StatusResponseDto> deleteSellerRegist(@PathVariable Long authorizationRequestId) {
        StatusResponseDto statusResponseDto = adminService.deleteSellerRegist(authorizationRequestId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }
}