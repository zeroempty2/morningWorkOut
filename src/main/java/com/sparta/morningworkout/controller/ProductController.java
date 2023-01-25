package com.sparta.morningworkout.controller;


import com.sparta.morningworkout.dto.PageDto;
import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.search.ProductResponseSearchByNameDto;
import com.sparta.morningworkout.dto.product.ProductUpdateRequestDto;
import com.sparta.morningworkout.security.UserDetailsImpl;
import com.sparta.morningworkout.service.serviceInterface.ProductService;
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
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity showProductsList(
            @RequestBody PageDto pageDto
    ) {
        Page<ProductResponseDto> productResponseDtos = productService.showProductList(pageDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(productResponseDtos);
    }

    @GetMapping("/list/seller/{sellerId}")
    public ResponseEntity showProductBySeller(@PathVariable Long sellerId, @RequestBody PageDto pageDto) {
        Page<ProductResponseDto> productResponseDtos = productService.showProductBySeller(sellerId, pageDto);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtos);
    }

    @PostMapping("/seller")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String msg = productService.addProduct(productRequestDto, userDetails.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(new StatusResponseDto(HttpStatus.CREATED.value(), msg));
    }

    @PatchMapping("/seller/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequestDto productUpdateRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String msg = productService.updateProduct(productId, productUpdateRequestDto,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.OK).body(new StatusResponseDto(HttpStatus.OK.value(), msg));
    }

    @DeleteMapping("/seller/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        String msg = productService.deleteProduct(productId,userDetails.getUser());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new StatusResponseDto(HttpStatus.NO_CONTENT.value(), msg));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<ProductResponseDto>> searchByProductsName(@RequestParam int page,@RequestParam int size,@RequestParam String keyword){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        Page<ProductResponseDto>productResponseDtos =productService.searchByProductsName(page-1,size,keyword);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(productResponseDtos);
    }

    @GetMapping("/search/seller")
    public ResponseEntity<Page<ProductResponseSearchByNameDto>> searchBySellerName(@RequestParam int page, @RequestParam int size, @RequestParam String sellerName){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        Page<ProductResponseSearchByNameDto> productResponseSearchByNameDtos = productService.searchBySellerName(page-1,size,sellerName);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(productResponseSearchByNameDtos);
    }


}
