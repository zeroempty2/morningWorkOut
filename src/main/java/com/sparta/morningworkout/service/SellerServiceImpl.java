package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.customer.CustomerListResponseDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.sellers.SellerListResponseDto;
import com.sparta.morningworkout.entity.CustomerRequestList;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.repository.CustomerRequestListRepository;
import com.sparta.morningworkout.repository.ProductRepository;
import com.sparta.morningworkout.service.serviceInterface.SellerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {


    private final CustomerRequestListRepository customerRequestListRepository;
    private final ProductRepository productRepository;
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerListResponseDto> showCustomerList(int page,User user) {

        Pageable pageable = PageRequest.of(page,10);
        Page<CustomerRequestList> customerList = customerRequestListRepository.findAllBySellerId(user.getId(),pageable);

        if(customerList==null){
            throw new IllegalArgumentException("판매요청한 구매자가 없습니다!");
        }

        return new PageImpl<>(customerList.stream().map(CustomerListResponseDto::new).collect(Collectors.toList()));


    }

    @Override
    @Transactional
    public String acceptBuyRequest(long customerId) {

        //API에서 ID값 없는데 줘야할듯함
        CustomerRequestList customerRequestList = customerRequestListRepository.findByUserId(customerId);

        if(customerRequestList==null){
            throw new IllegalArgumentException("해당 사용자의 판매요청이 없습니다!");
        }
        customerRequestList.acceptBySeller();
        return "해당 사용자의 판매요청이 수락되었습니다!";

    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponseDto> showMyProducts(int page,User user) {

        Pageable pageable = PageRequest.of(page,10);
        Page<Product> products = productRepository.findAllByUserId(user.getId(),pageable);

       if(products==null){
           throw new IllegalArgumentException("내 판매상품 내역이 없습니다!");

       }
       return new PageImpl<>(products.stream().map(ProductResponseDto::new).collect(Collectors.toList()));
    }
}
