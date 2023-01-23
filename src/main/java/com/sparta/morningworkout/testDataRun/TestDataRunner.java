package com.sparta.morningworkout.testDataRun;

import com.sparta.morningworkout.dto.product.ProductRequestDto;
import com.sparta.morningworkout.entity.*;
import com.sparta.morningworkout.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TestDataRunner implements ApplicationRunner {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final ProductRepository productRepository;
    private final CustomerRequestListRepository customerRequestListRepository;
    private final PointRepository pointRepository;

    @Transactional
    @Override
    public void run(ApplicationArguments args) throws Exception {
        User testAdminUser = new User("관리자", passwordEncoder.encode("비밀번호"), UserRoleEnum.ADMIN);
        userRepository.save(testAdminUser);
        Profile testAdminProfile = new Profile(testAdminUser.getId(),"관리자");
        profileRepository.save(testAdminProfile);

        User testSellerUser = new User("Seller", passwordEncoder.encode("sellerpassword1"), UserRoleEnum.SELLER);
        userRepository.save(testSellerUser);
        Point testSeller = new Point("Seller1", 1000, null);
        pointRepository.save(testSeller);
        Profile testSellerProfile = Profile.builder().id(testSellerUser.getId()).nickname("테스트판매자").category(CategoryEnum.IT).infoContent("testSeller").build();
        profileRepository.save(testSellerProfile);

        User testCustomerUser = new User("Customer", passwordEncoder.encode("sellerpassword1"), UserRoleEnum.CUSTOMER);
        userRepository.save(testCustomerUser);
        Point testCustomer = new Point("Customer", 55000, null);
        pointRepository.save(testCustomer);
        Profile testCustomerProfile = Profile.builder().id(testCustomerUser.getId()).nickname("테스트유저").build();
        profileRepository.save(testCustomerProfile);

        User testCustomerUser2 = new User("Customer2", passwordEncoder.encode("sellerpassword1"), UserRoleEnum.CUSTOMER);
        userRepository.save(testCustomerUser2);
        Point testCustomer2 = new Point("Customer2", 30000, null);
        pointRepository.save(testCustomer2);
        Profile testCustomerProfile2 = Profile.builder().id(testCustomerUser2.getId()).nickname("테스트유저2").build();
        profileRepository.save(testCustomerProfile2);

        CustomerRequestList customerRequestList1 = CustomerRequestList.builder().userId(testCustomerUser.getId()).sellerId(testSellerUser.getId()).productId(1L).build();
        customerRequestListRepository.save(customerRequestList1);
        CustomerRequestList customerRequestList2 = CustomerRequestList.builder().userId(testCustomerUser2.getId()).sellerId(testSellerUser.getId()).productId(2L).build();
        customerRequestListRepository.save(customerRequestList2);
        List<ProductRequestDto> productRequestDtoList = new ArrayList<>();

        productRequestDtoList.add(new ProductRequestDto("맥북프로",5000000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("아이패드프로",2000000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("SSD 1T",100000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("아이폰14ProMax",1750000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("에어팟",250000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("맥북에어",200000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("아이패드에어",1000000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("SSD 2T",200000, CategoryEnum.IT));
        productRequestDtoList.add(new ProductRequestDto("아이폰14",1000000, CategoryEnum.IT));

        productRequestDtoList.add(new ProductRequestDto("과자",3000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("김치",20000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("삼겹살 1Kg",20000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("김",3000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("참치",3500, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("고추참치",5000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("고구마 1Kg",10000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("우유 1L",5000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("계란",4000, CategoryEnum.FOOD));
        productRequestDtoList.add(new ProductRequestDto("등심 1Kg",15500, CategoryEnum.FOOD));

        productRequestDtoList.add(new ProductRequestDto("셔츠(흰색)",50000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("자켓",250000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("롱패딩",200000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("바지",70000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("넥타이",150000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("블라우스(흰색)",50000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("신발",250000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("숏패딩",200000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("레깅스",70000, CategoryEnum.FASHION));
        productRequestDtoList.add(new ProductRequestDto("드레스",150000, CategoryEnum.FASHION));

        productRequestDtoList.add(new ProductRequestDto("망치",10000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("드라이버",5000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("전동드릴",100000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("니퍼",15000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("그라인더",60000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("전동망치",30000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("전동드라이버",55000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("인두기",100000, CategoryEnum.TOOLS));
        productRequestDtoList.add(new ProductRequestDto("니퍼(중)",15000, CategoryEnum.TOOLS));

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