package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.product.ProductUpdateRequestDto;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.service.serviceInterface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public String addProduct(ProductRequestDto productRequestDto) {
        Product product = Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategoryEnum())
              //  .userId(user.getId()) 해당 유저 부분은 추후 시큐리티에서 인증된 user를 넣어서 진행할 예정
                .build();
        productRepository.save(product);
        return "해당 상품을 등록 완료했습니다";
    }

    @Override
    @Transactional
    public String updateProduct(Long productId, ProductUpdateRequestDto productUpdateRequestDto) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 제품은 등록되지 않은 제품입니다"));
        product.update(productUpdateRequestDto);
        return "해당 제품의 가격이 업데이트 되었습니다";
    }

    @Override
    @Transactional
    public String deleteProduct(Long productId) {
        productRepository.deleteById(productId);
        return "삭제완료";
    }

    @Override
    @Transactional
    public List<ProductResponseDto> showProductList() {
        List<ProductResponseDto> products = productRepository.findAll().stream().map(ProductResponseDto::new).toList();
        return products;
    }

    @Override
    @Transactional
    public List<ProductResponseDto> showProductBySeller(Long sellerId) {
        List<ProductResponseDto> productResponseDtos = productRepository.findAllByUserId(sellerId).stream().map(ProductResponseDto::new).collect(Collectors.toList());
        return productResponseDtos;
    }
}
