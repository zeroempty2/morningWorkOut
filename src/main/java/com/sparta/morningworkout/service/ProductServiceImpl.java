package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.PageDto;
import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.product.ProductUpdateRequestDto;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.service.serviceInterface.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional // 통합테스트를 통해 해당 과정이 올바르게 돌아가는지 확인 ★
    public String addProduct(ProductRequestDto productRequestDto, User user) {
        Product product = Product.builder()
                .productName(productRequestDto.getProductName())
                .price(productRequestDto.getPrice())
                .category(productRequestDto.getCategoryEnum())
                .userId(user.getId()) //해당 유저 부분은 추후 시큐리티에서 인증된 user를 넣어서 진행할 예정
                .build();
        productRepository.save(product);
        return "해당 상품을 등록 완료했습니다";
    }

    @Override
    @Transactional // 트랜잭셔널 유무로 업데이트 상황
    public String updateProduct(Long productId, ProductUpdateRequestDto productUpdateRequestDto, User user) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 제품은 등록되지 않은 제품입니다"));
        if (user.checkUser(product.getUserId())) {
            product.update(productUpdateRequestDto);
            return "해당 제품의 가격이 업데이트 되었습니다";
        } else {
            throw new IllegalArgumentException("해당 유저는 해당 상품에 관해 업데이트를 할 수 있는 권한이 존재하지않습니다");
        }
    }

    @Override
    @Transactional
    public String deleteProduct(Long productId, User user) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 제품 아이디는 존재하지 않습니다"));
        if (user.checkUser(product.getUserId())) {
            productRepository.delete(product);
            return "삭제완료";
        } else {
            throw new IllegalArgumentException("해당 유저는 상품에 대한 권한이 없습니다");
        }
    }

    @Override
    @Transactional
    public Page<ProductResponseDto> showProductList(PageDto pageDto) {
        Pageable pageable = makePage(pageDto);

        Page<Product> products = productRepository.findAll(pageable);

        return new PageImpl<>(products.stream().map(ProductResponseDto::new).collect(Collectors.toList()));
    }

    @Override
    @Transactional
    // 구매자가 판매자의 상품 전체조회
    public Page<ProductResponseDto> showProductBySeller(Long sellerId, PageDto pageDto) {
        Pageable pageable = makePage(pageDto);
        Page<Product> products = productRepository.findAllByUserId(sellerId, pageable);
        return new PageImpl<>(products.stream().map(ProductResponseDto::new).collect(Collectors.toList()));
    }

    @Override
    public Product findProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException("해당 상품은 존재하지 않습니다"));
    }

    // Pageable 생성 메서드
    public Pageable makePage(PageDto pageDto) {
        Sort.Direction direction = pageDto.isAsc() ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, pageDto.getSortBy());
        return PageRequest.of(pageDto.getPage() - 1, pageDto.getSize(), sort);
    }


}
