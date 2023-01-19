package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.sellers.SellerListResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CustomerService {

    Page<SellerListResponseDto> showSellerList(int page,int size);
   // ShowSellerProfileResponseDto showSellerProfile(long sellerId);
    String requestBuyProducts(long userId,long sellerId);
}
