package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.admin.UserContentsResponseDto;
import com.sparta.morningworkout.dto.admin.SellerRegistResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {
    Page<UserContentsResponseDto> showCustomerList(int page,int size);
    Page<UserContentsResponseDto>showCustomerListBySearchingNickname(int page, int size, String keyword);
    Page<UserContentsResponseDto> showSellerList(int page,int size);
    List<SellerRegistResponseDto> showSellerRegistList();
    StatusResponseDto acceptSellerRegist(Long authorizationRequestId);
    StatusResponseDto deleteSellerRegist(Long authorizationRequestId);
    Page<UserContentsResponseDto>showSellerListBySearchingNickname(int page, int size, String keyword);
}
