package com.sparta.morningworkout.controller;

import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.customer.CustomerRequestResponseDto;
import com.sparta.morningworkout.dto.search.CustomerListBySellerDto;

import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.search.ProductResponseSearchByNameDto;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.SellerServiceImpl;
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
@RequestMapping( "/sellers")
public class SellerController {

    private final SellerServiceImpl sellerService;

    @GetMapping("/customers")
    public ResponseEntity<Page<CustomerRequestResponseDto>> showCustomerList(@RequestParam int page, @RequestParam int size, @AuthenticationPrincipal UserDetailsImpl userDetails){
       Page<CustomerRequestResponseDto> customerListResponseDtoList = sellerService.showCustomerList(page-1,size,userDetails.getUserId());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
       return ResponseEntity.status(HttpStatus.OK).headers(headers).body(customerListResponseDtoList);

    }

    //seller 입장에서 구매자의 이름을 가지고 구매요청자 리스트를 검색하는 기능.
    @GetMapping("/customer/requests/search")
    public ResponseEntity<Page<CustomerListBySellerDto>> searchByCustomerName(@RequestParam int page, @RequestParam int size, @RequestParam String keyword, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Page<CustomerListBySellerDto> customerListResponseDtos= sellerService.searchByCustomerName(page-1,size,keyword,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(customerListResponseDtos);
    }

    @PostMapping("/customers/{customerRequestId}")
    public ResponseEntity acceptBuyRequest(@PathVariable long customerRequestId){
        String msg = sellerService.acceptBuyRequest(customerRequestId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new StatusResponseDto(HttpStatus.OK.value(), msg));
    }

    @GetMapping("/products/list")
    public ResponseEntity<Page<ProductResponseDto>> showMyProducts(@RequestParam int page,@RequestParam int size ,@AuthenticationPrincipal UserDetailsImpl userDetails){
        Page<ProductResponseDto> products = sellerService.showMyProducts(page-1,size,userDetails.getUser());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(products);
    }

    @GetMapping("/products/list/search")
    public ResponseEntity<Page<ProductResponseDto>> searchMyProductsByKeyword(@RequestParam int page,@RequestParam int size,@RequestParam String keyword ,@AuthenticationPrincipal UserDetailsImpl userDetails){
        Page<ProductResponseDto> products = sellerService.searchMyProductsByKeyword(page-1,size,keyword,userDetails.getUser());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(products);
    }

}
