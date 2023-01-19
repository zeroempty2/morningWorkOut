package com.sparta.morningworkout.testDataRun;

import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.entity.*;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User testAdminUser = new User("관리자", passwordEncoder.encode("비밀번호"), UserRoleEnum.ADMIN);
        userRepository.save(testAdminUser);
        Profile testAdminProfile = new Profile(testAdminUser.getId(),"관리자");
        profileRepository.save(testAdminProfile);
        User testSellerUser = new User("Seller1", passwordEncoder.encode("sellerpassword1"), UserRoleEnum.SELLER);
        userRepository.save(testSellerUser);
        Profile testSellerProfile = Profile.builder().id(testSellerUser.getId()).nickname("테스트판매자").category(CategoryEnum.IT).infoContent("testSeller").build();
        profileRepository.save(testSellerProfile);

        List<ProductRequestDto> productRequestDtoList = new ArrayList<>();

        productRequestDtoList.add(new ProductRequestDto("맥북프로",5000000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("아이패드프로",2000000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("SSD",100000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("아이폰14ProMax",1750000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("에어팟",250000, CategoryEnum.IT));

        productRequestDtoList.add(new ProductRequestDto("과자",3000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("김치",20000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("삼겹살",20000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("김",3000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("참치",3500, CategoryEnum.FOOD));

        productRequestDtoList.add(new ProductRequestDto("셔츠",50000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("자켓",250000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("패딩",200000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("바지",70000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("넥타이",150000, CategoryEnum.FASHION));

        productRequestDtoList.add(new ProductRequestDto("망치",10000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("드라이버",5000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("전동드릴",100000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("니퍼",15000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("그라인더",60000, CategoryEnum.TOOLS));

        createProducts(testSellerUser,productRequestDtoList);
    }
    private void createProducts(User user, List<ProductRequestDto> productRequestDtoList){
        long sellerId = user.getId();
        for(ProductRequestDto productRequestDto : productRequestDtoList){
            Product product = Product.builder()
                    .userId(sellerId)
                    .productName(productRequestDto.getProductName())
                    .price(productRequestDto.getPrice())
                    .category(productRequestDto.getCategoryEnum())
                    .build();
            productRepository.save(product);
        }
    }
}