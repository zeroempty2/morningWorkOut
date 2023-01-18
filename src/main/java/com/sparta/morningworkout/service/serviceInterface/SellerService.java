package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.customer.CustomerListResponseDto;
import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SellerService {
    Page<CustomerListResponseDto> showCustomerList(int page,User user);
    String acceptBuyRequest(long customerId);
    Page<ProductResponseDto> showMyProducts(int page,User user);
}
