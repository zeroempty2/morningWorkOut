package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.PageDto;
import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.search.ProductResponseSearchByNameDto;
import com.sparta.morningworkout.dto.product.ProductUpdateRequestDto;
import com.sparta.morningworkout.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    String addProduct(ProductRequestDto productRequestDto, User user);
    String updateProduct(Long productId, ProductUpdateRequestDto productUpdateRequestDto, User user);
    String deleteProduct(Long productId, User user);
    Page<ProductResponseDto> showProductList(Pageable pageDto);
    Page<ProductResponseDto> showProductBySeller(Long sellerId, Pageable pageDto);

    Page<ProductResponseDto> searchByProductsName(int page,int size,String keyword);
   Page<ProductResponseSearchByNameDto> searchBySellerName(int page, int size, String sellerName);

}
