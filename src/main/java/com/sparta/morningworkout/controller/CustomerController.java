package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.SellerListResponseDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.service.CustomerServiceImpl;
import com.sparta.morningworkout.service.SellerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/customers")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @GetMapping("/sellers")
    public ResponseEntity<List<SellerListResponseDto>> showSellerList(){

        List<SellerListResponseDto> sellerList = customerService.showSellerList();

        return ResponseEntity.status(HttpStatus.CREATED).body(sellerList);

    }


    @PostMapping("/products/{productsId}")
    public ResponseEntity requestBuyProducts(@PathVariable Long productsId, User user){ // UserDetails 자리
        String msg = customerService.requestBuyProducts(user.getId(),productsId);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
