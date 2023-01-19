package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.profile.ShowCustomerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;

public interface ProfileService {
    void updateCustomerProfile(long id, UpdateCustomerProfileRequestDto request, String username);
    void updateSellerProfile(long id, UpdateSellerProfileRequestDto request, String username);
    ShowCustomerProfileResponseDto showCustomerProfile(long id, String username);
    ShowSellerProfileResponseDto showSellerProfile(long id, String username);
}
