package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.entity.CategoryEnum;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductServiceImpl productService;


    @Test
    void addProduct() {

        //given
        Product product = mock(Product.class);
        ProductRequestDto productRequestDto = mock(ProductRequestDto.class);

        productRequestDto = ProductRequestDto.builder()
                .productName("물건1")
                .price(20000)
                .categoryEnum(CategoryEnum.IT)
                .build();

        product = Product.builder()
                .productName(productRequestDto.getProductName())
                .category(productRequestDto.getCategoryEnum())
                .price(productRequestDto.getPrice())
                .build();

        //when
        String result = productService.addProduct(productRequestDto);


        Assertions.assertSame("해당 상품을 등록 완료했습니다", result);

    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    @Test
    void showProductList() {
    }

    @Test
    void showProductBySeller() {
    }
}
