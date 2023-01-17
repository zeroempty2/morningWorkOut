package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.SellerRegistResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.UserListResponseDto;
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
@RequestMapping( "/admin")
public class AdminController {
    private final AdminServiceImpl adminService;
    @GetMapping("/customers")
    public ResponseEntity<Page<UserListResponseDto>> showCustomerList(@RequestParam int page){
        Page<UserListResponseDto> responseDto =  adminService.showCustomerList(page);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
    @GetMapping("/sellers")
    public ResponseEntity<Page<UserListResponseDto>> showSellerList(@RequestParam int page){
        Page<UserListResponseDto> responseDto =  adminService.showSellerList(page);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
    @GetMapping("/authorizations")
    public ResponseEntity<List<SellerRegistResponseDto>> showSellerRegistList(){
        List<SellerRegistResponseDto> responseDto =  adminService.showSellerRegistList();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
    @PostMapping("/authorization/{authorizationRequestId}")
    public ResponseEntity<StatusResponseDto> acceptSellerRegist(@PathVariable Long authorizationRequestId){
        StatusResponseDto statusResponseDto =  adminService.acceptSellerRegist(authorizationRequestId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }
    @DeleteMapping("/authorization/{authorizationRequestId}")
    public ResponseEntity<StatusResponseDto> deleteSellerRegist(@PathVariable Long authorizationRequestId){
        StatusResponseDto statusResponseDto =  adminService.deleteSellerRegist(authorizationRequestId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(statusResponseDto);
    }
}

