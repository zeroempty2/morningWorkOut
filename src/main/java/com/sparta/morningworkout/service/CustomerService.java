package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.SellerListResponseDto;
import com.sparta.morningworkout.entity.User;

import java.util.List;

public interface CustomerService {

    List<SellerListResponseDto> showSellerList();
    void showSellerProfile();
    String requestBuyProducts(Long userId,Long sellerId);
}
