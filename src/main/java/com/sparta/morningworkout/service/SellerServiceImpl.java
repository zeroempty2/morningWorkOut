package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.customer.CustomerRequestResponseDto;
import com.sparta.morningworkout.dto.search.CustomerListBySellerDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.entity.CustomerRequest;
import com.sparta.morningworkout.entity.Point;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.repository.CustomerRequestListRepository;
import com.sparta.morningworkout.repository.PointRepository;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.service.serviceInterface.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {


    private final CustomerRequestListRepository customerRequestListRepository;
    private final ProductRepository productRepository;
    private final PointRepository pointRepository;
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerRequestResponseDto> showCustomerList(int page, int size, long sellerId) {
        Pageable pageable = PageRequest.of(page,size);
        return customerRequestListRepository.findAllCustomersBySellerId(sellerId,pageable);

       // return new PageImpl<>(customerList.stream().map(CustomerListResponseDto::new).collect(Collectors.toList()));

    }

    @Override
    @Transactional
    public String acceptBuyRequest(long customerRequestId) {

        //API에서 ID값 없는데 줘야할듯함 -> ?? 왜 유저 id를 받습니까 ㄹㅇ니ㅏ러ㅏㅣ너아ㅣㅓㄴㅇ리ㅏㄴ
        CustomerRequest customerRequest = customerRequestListRepository.findById(customerRequestId).orElseThrow(
                () -> new IllegalArgumentException("유효하지 않은 구매요청입니다")
        );
        if(customerRequest.isAccepted()){
            return "이미 처리된 요청입니다";
        }

        if(customerRequest ==null){
            throw new IllegalArgumentException("해당 사용자의 판매요청이 없습니다!");
        }
        customerRequest.acceptBySeller();
        if(customerRequest.isUsePoint()){
            Point customerPoint = pointRepository.findById(customerRequest.getUserId()).orElseThrow(
                    () -> new IllegalArgumentException("유저의 정보가 잘못되었습니다")
            );
            Point sellerPoint = pointRepository.findById(customerRequest.getSellerId()).orElseThrow(
                    () -> new IllegalArgumentException("판매자 정보가 잘못되었습니다")
            );
            int productPoint = productRepository.findById(customerRequest.getProductId()).orElseThrow(
                    () -> new IllegalArgumentException("상품 정보가 잘못되었습니다")
            ).getPoint();
            customerPoint.minusPoint(productPoint);
            sellerPoint.plusPoint(productPoint);
            return "해당 사용자의 판매요청이 수락되었습니다!";
        }
        return "해당 사용자의 판매요청이 수락되었습니다!";
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> showMyProducts(int page,int size,User user) {
       //Sort sort = Sort.by(Sort.Direction.ASC ,"price");

        Pageable pageable = PageRequest.of(page,size);
        Page<Product> products = productRepository.findAllByUserId(user.getId(),pageable);

       if(products==null){
           throw new IllegalArgumentException("내 판매상품 내역이 없습니다!");

       }
       return products.map(ProductResponseDto::new);
       //return new PageImpl<>(products.stream().map(ProductResponseDto::new).collect(Collectors.toList()),pageable,products.getTotalPages());
    }

    public Page<CustomerListBySellerDto> searchByCustomerName(int page, int size, String keyword,User user) {
        Pageable pageable = PageRequest.of(page,size);
        return customerRequestListRepository.findAllByCustomerName(user.getId(),keyword,pageable);
    }

    public Page<ProductResponseDto> searchMyProductsByKeyword(int page, int size,String keyword, User user) {
        Pageable pageable = PageRequest.of(page,size);
        return productRepository.findAllBySellerIdByProductName(keyword,user.getId(),pageable);
    }
}
