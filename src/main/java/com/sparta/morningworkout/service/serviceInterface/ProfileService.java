package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.profile.ShowCustomerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;

public interface ProfileService {
    void updateProfile(long id, UpdateCustomerProfileRequestDto request, long userId);
    void updateSellerProfile(long id, UpdateSellerProfileRequestDto request, String username);
    ShowCustomerProfileResponseDto showMyProfile(long id);
    ShowSellerProfileResponseDto showProfile(long id);
}
