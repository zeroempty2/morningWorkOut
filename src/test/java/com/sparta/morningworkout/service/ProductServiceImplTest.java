package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.PageDto;
import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.product.ProductUpdateRequestDto;
import com.sparta.morningworkout.entity.CategoryEnum;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import com.sparta.morningworkout.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {


    @Mock
    private ProductRepository productRepository;



    @InjectMocks
    private ProductServiceImpl productService;
    @Test
    @DisplayName("상품추가")
    void addProduct() {

        //given
        ProductRequestDto productResponseDto = new ProductRequestDto();

        User user = new User();


        //when
        String msg = productService.addProduct(productResponseDto,user);

        //then
        assertThat(msg).isEqualTo("해당 상품을 등록 완료했습니다");

        verify(productRepository,times(1)).save(any(Product.class));

    }

    @Test
    @DisplayName("상품 정보 수정 정상")
    void updateProduct()  {
        //given

        ProductUpdateRequestDto productUpdateRequestDto = new ProductUpdateRequestDto(7777);

        User user = mock(User.class);

        Product product = Product.builder().productName("computer").price(1000).build();
        //given(user.checkUser(anyLong())).willReturn(true);
        given(user.checkUser(anyLong())).willReturn(true);
        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(product));
        //when

       String msg = productService.updateProduct(anyLong(),productUpdateRequestDto,user);
        //then
        assertThat(msg).isEqualTo("해당 제품의 가격이 업데이트 되었습니다");
    }

    @Test
    @DisplayName("상품내역 전체보기")
    void showProductList() {

        //given
        PageDto pageDto = new PageDto(1,1,"id",true);
        Pageable pageable = productService.makePage(pageDto);
        given(productRepository.findAll(pageable)).willReturn(Page.empty());

        //when

        Page<ProductResponseDto> products = productService.showProductList(pageDto);
        //then
        verify(productRepository,times(1)).findAll(pageable);
        assertThat(products).isEmpty();

    }
}