package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.ProductResponseDto;

import java.util.List;

public interface ProductService {
    void addProduct();
    void updateProduct();
    void deleteProduct();
    List<ProductResponseDto> showProductList();
    void showProductBySeller();
}
