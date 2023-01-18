package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.profile.ShowCustomerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;
import com.sparta.morningworkout.entity.CategoryEnum;

public interface ProfileService {
    void updateCustomerProfile(long id, UpdateCustomerProfileRequestDto request);
    void updateSellerProfile(long id, UpdateSellerProfileRequestDto request);
    ShowCustomerProfileResponseDto showCustomerProfile(long id, String nickname);
    ShowSellerProfileResponseDto showSellerProfile(long id, String nickname, String infoContent, CategoryEnum category);
}
