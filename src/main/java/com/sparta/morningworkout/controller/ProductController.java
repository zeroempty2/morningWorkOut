package com.sparta.morningworkout.controller;


import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.product.ProductUpdateRequestDto;
import com.sparta.morningworkout.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity showProductsList() {
        List<ProductResponseDto> productResponseDtos = productService.showProductList();

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtos);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) {

        String msg = productService.addProduct(productRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(new StatusResponseDto(HttpStatus.CREATED.value(), msg));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateRequestDto productUpdateRequestDto) {
        String msg = productService.updateProduct(productId, productUpdateRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(new StatusResponseDto(HttpStatus.OK.value(), msg));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity deleteProduct(@PathVariable Long productId) {
        String msg = productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new StatusResponseDto(HttpStatus.NO_CONTENT.value(), msg));
    }

    @GetMapping("/list/seller/{sellerId}")
    public ResponseEntity showProductBySeller(@PathVariable Long sellerId){
       List<ProductResponseDto> productResponseDtos = productService.showProductBySeller(sellerId);
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtos);
    }
}
