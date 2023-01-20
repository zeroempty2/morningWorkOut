package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.admin.SearchNicknameResponseDto;
import com.sparta.morningworkout.dto.admin.SellerRegistResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.admin.UserListResponseDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AdminService {
    Page<UserListResponseDto> showCustomerList(int page,int size);
    Page<SearchNicknameResponseDto>showCustomerListBySearchingNickname(int page, int size, String keyword);
    Page<UserListResponseDto> showSellerList(int page,int size);
    List<SellerRegistResponseDto> showSellerRegistList();
    StatusResponseDto acceptSellerRegist(Long authorizationRequestId);
    StatusResponseDto deleteSellerRegist(Long authorizationRequestId);
    Page<SearchNicknameResponseDto>showSellerListBySearchingNickname(int page, int size, String keyword);
}
