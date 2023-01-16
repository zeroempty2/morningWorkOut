package com.sparta.morningworkout.controller;


import com.sparta.morningworkout.dto.ProductResponseDto;
import com.sparta.morningworkout.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/list")
    public ResponseEntity showProductsList(
    ) {
        List<ProductResponseDto> productResponseDtos = productService.showProductList();
        return ResponseEntity.status(HttpStatus.OK).body(productResponseDtos);
    }


}
