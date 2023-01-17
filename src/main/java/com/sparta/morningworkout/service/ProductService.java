package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.product.ProductUpdateRequestDto;

import java.util.List;

public interface ProductService {
    String addProduct(ProductRequestDto productRequestDto);
    String updateProduct(Long productId, ProductUpdateRequestDto productUpdateRequestDto);
    String deleteProduct(Long productId);
    List<ProductResponseDto> showProductList();
    List<ProductResponseDto> showProductBySeller(Long sellerId);
}
