package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.Point.PointUpdateRequestDto;
import com.sparta.morningworkout.dto.admin.UserContentsResponseDto;
import com.sparta.morningworkout.dto.admin.SellerRegistResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
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
    public ResponseEntity<Page<UserContentsResponseDto>> showCustomerList(
            @RequestParam int page,
            @RequestParam int size) {
        Page<UserContentsResponseDto> responseDto = adminService.showCustomerList(page - 1, size);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    @GetMapping("/sellers")
    public ResponseEntity<Page<UserContentsResponseDto>> showSellerList(
            @RequestParam int page,
            @RequestParam int size) {
        Page<UserContentsResponseDto> responseDto = adminService.showSellerList(page - 1, size);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
    @GetMapping("/customers/search/nickname")
    public ResponseEntity<Page<UserContentsResponseDto>> showCustomerListBySearchingNickname(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(value = "keyword") String keyword
    ) {
        Page<UserContentsResponseDto> responseDto = adminService.showCustomerListBySearchingNickname(page - 1, size, keyword);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
    @GetMapping("/sellers/search/nickname")
    public ResponseEntity<Page<UserContentsResponseDto>> showSellerListBySearchingNickname(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(value = "keyword") String keyword
    ) {
        Page<UserContentsResponseDto> responseDto = adminService.showSellerListBySearchingNickname(page - 1, size, keyword);
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

    @PatchMapping("/authorization/delete/{userId}")
    public ResponseEntity<StatusResponseDto> deleteSellerRegist(@PathVariable Long userId) {
        StatusResponseDto statusResponseDto = adminService.deleteSellerRegist(userId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }
    @PostMapping("/point/{userId}")
    public ResponseEntity<StatusResponseDto> givePoint(@PathVariable Long userId, @RequestBody PointUpdateRequestDto pointUpdateRequestDto) {
        StatusResponseDto statusResponseDto = adminService.givePoint(userId,pointUpdateRequestDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }
}