package com.sparta.morningworkout.service.serviceInterface;

import java.util.List;

import com.sparta.morningworkout.dto.profile.ShowCustomerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;

public interface ProfileService {
    void updateCustomerProfile(long id, UpdateCustomerProfileRequestDto request);
    void updateSellerProfile(long id, UpdateSellerProfileRequestDto request);
    ShowCustomerProfileResponseDto showCustomerProfile(String nickname);
    List<ShowSellerProfileResponseDto> showSellerProfile();
}
