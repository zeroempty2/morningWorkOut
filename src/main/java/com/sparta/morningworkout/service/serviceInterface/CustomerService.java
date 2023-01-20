package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.admin.UserContentsResponseDto;
import com.sparta.morningworkout.dto.sellers.SellerListResponseDto;
import org.springframework.data.domain.Page;



public interface CustomerService {

    Page<SellerListResponseDto> showSellerList(int page,int size);
   // ShowSellerProfileResponseDto showSellerProfile(long sellerId);
    String requestBuyProducts(long userId,long sellerId);
    Page<UserContentsResponseDto> searchSellerListBySellerNickname(int page, int size, String nickName);
}
