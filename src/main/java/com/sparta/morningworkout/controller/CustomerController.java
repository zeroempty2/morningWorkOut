package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.sellers.SellerListResponseDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping("/sellers")
    public ResponseEntity<Page<SellerListResponseDto>> showSellerList(@RequestParam int page){

        Page<SellerListResponseDto> sellerList = customerService.showSellerList(page-1);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(sellerList);

    }


    @PostMapping("/products/{productsId}")
    public ResponseEntity requestBuyProducts(@PathVariable long productsId, User user){ // UserDetails 자리
        String msg = customerService.requestBuyProducts(user.getId(),productsId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new StatusResponseDto(HttpStatus.OK.value(),msg));
    }
}
