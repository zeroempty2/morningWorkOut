package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.customer.CustomerRequestResponseDto;
import com.sparta.morningworkout.dto.search.CustomerListBySellerDto;

import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.entity.User;
import org.springframework.data.domain.Page;

public interface SellerService {
    Page<CustomerRequestResponseDto>  showCustomerList(int page, int size, long sellerId);
    String acceptBuyRequest(long customerId);
    Page<ProductResponseDto> showMyProducts(int page,int size,User user);

    Page<CustomerListBySellerDto> searchByCustomerName(int page, int size, String keyword, User user);
}
