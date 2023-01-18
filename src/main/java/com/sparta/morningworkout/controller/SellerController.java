package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.customer.CustomerListResponseDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.service.SellerServiceImpl;
import lombok.Getter;
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
@RequestMapping( "/sellers")
public class SellerController {

    private final SellerServiceImpl sellerService;

    @GetMapping("/customers")
    public ResponseEntity<Page<CustomerListResponseDto>> showCustomerList(@RequestParam int page, User user){
       Page<CustomerListResponseDto> customerListResponseDtoList = sellerService.showCustomerList(page-1,user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
       return ResponseEntity.status(HttpStatus.OK).headers(headers).body(customerListResponseDtoList);

    }

    @PostMapping("/customers/{customerId}")
    public ResponseEntity acceptBuyRequest(@PathVariable long customerId){

        String msg = sellerService.acceptBuyRequest(customerId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new StatusResponseDto(HttpStatus.OK.value(), msg));
    }

    @GetMapping("/products/list")
    public ResponseEntity<Page<ProductResponseDto>> showMyProducts(@RequestParam int page,User user){
        Page<ProductResponseDto> products = sellerService.showMyProducts(page-1,user);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(products);
    }
}
