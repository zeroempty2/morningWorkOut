package com.sparta.morningworkout.service;


import com.sparta.morningworkout.dto.sellers.SellerListResponseDto;
import com.sparta.morningworkout.entity.CustomerRequestList;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import com.sparta.morningworkout.repository.CustomerRequestListRepository;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.repository.UserRepository;
import com.sparta.morningworkout.service.serviceInterface.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final UserRepository userRepository;
    //private final ProfileService profileService;
    private final CustomerRequestListRepository customerRequestListRepository;

    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<SellerListResponseDto> showSellerList(int page) {
        Pageable pageable = PageRequest.of(page,10);

        Page<User> sellerList = userRepository.findAllByRoleOrderByIdDesc(UserRoleEnum.SELLER,pageable);

      return new PageImpl<>(sellerList.stream().map(SellerListResponseDto::new).collect(Collectors.toList()));

    }

    @Override
    public void showSellerProfile() {

        //프로필 인터페이스가 구현되고, 그 서비스 딴 로직을 호출해야 되지 않나 싶다.
    }

    @Override
    @Transactional
    public String requestBuyProducts(long userId,long productId) {

        //시큐리티를 걸쳐 로그인 된 Customer가 구매요청을 보내는 것 이므로 User user -> user.getUserId(); 가필요할듯함.
        //여기서 자신의 상품은 자신이 판매 요청을 할 수 없게 판단하는 로직을 넣을것인가 말것인가 의논 필요
        //현재 로그인된 사람의 userId와 판매요청이 담겨있는 CustomerRequestList에서 sellerId를 빼와서 같으면 던진다...!

        //만약 값이 찾아 진다면 이미 구매요청이 들어간 상품이다.
        if(customerRequestListRepository.findByProductId(productId)!=null){

            throw new IllegalArgumentException("이미 구매요청이 걸려있는 상품입니다!");
        }else{
            Product product = productRepository.findById(productId).orElseThrow(
                    ()->new IllegalArgumentException("구매하고자 하는 상품이 없습니다!")
            );


            customerRequestListRepository.save(new CustomerRequestList(userId,product.getUserId(),product.getId()));
            return "구매요청이 완료되었습니다!";
        }


    }
}
