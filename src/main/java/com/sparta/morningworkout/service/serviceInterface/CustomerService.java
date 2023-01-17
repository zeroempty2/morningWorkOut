package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.sellers.SellerListResponseDto;

import java.util.List;

public interface CustomerService {

    List<SellerListResponseDto> showSellerList();
    void showSellerProfile();
    String requestBuyProducts(Long userId,Long sellerId);
}
