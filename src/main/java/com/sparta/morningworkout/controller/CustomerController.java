package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.admin.UserContentsResponseDto;
import com.sparta.morningworkout.dto.sellers.SellerListResponseDto;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;


@RestController
@RequiredArgsConstructor
@RequestMapping( "/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping("/sellers")
    public ResponseEntity<Page<SellerListResponseDto>> showSellerList(@RequestParam int page,@RequestParam int size){

        Page<SellerListResponseDto> sellerList = customerService.showSellerList(page-1,size);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(sellerList);

    }
    @GetMapping("/search/seller")
    public ResponseEntity<Page<UserContentsResponseDto>> searchSellerListBySellerNickname(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(value = "keyword") String keyword){
        Page<UserContentsResponseDto> responseDto = customerService.searchSellerListBySellerNickname(page - 1, size, keyword);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
    @PostMapping("/products/{productsId}")
    public ResponseEntity<StatusResponseDto> requestBuyProducts(@PathVariable long productsId, @AuthenticationPrincipal UserDetailsImpl userDetails){ // UserDetails 자리
        StatusResponseDto responseDto = customerService.requestBuyProducts(userDetails.getUser().getId(),productsId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }

    @PostMapping("/products/point/{productsId}")
    public ResponseEntity<StatusResponseDto> requestBuyProductsByPoint(@PathVariable long productsId, @AuthenticationPrincipal UserDetailsImpl userDetails){ // UserDetails 자리
        StatusResponseDto responseDto = customerService.requestBuyProductsByPoint(userDetails.getUser().getId(),productsId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.ok().headers(headers).body(responseDto);
    }
}
