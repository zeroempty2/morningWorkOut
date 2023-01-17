package com.sparta.morningworkout.service;

import java.util.List;

import com.sparta.morningworkout.dto.ShowCustomerProfileResponse;
import com.sparta.morningworkout.dto.ShowSellerProfileResponse;
import com.sparta.morningworkout.dto.UpdateCustomerProfileRequest;
import com.sparta.morningworkout.dto.UpdateSellerProfileRequest;

public interface ProfileService {
    void updateCustomerProfile(long id, UpdateCustomerProfileRequest request);
    void updateSellerProfile(long id, UpdateSellerProfileRequest request);
    ShowCustomerProfileResponse showCustomerProfile(String nickname);
    List<ShowSellerProfileResponse> showSellerProfile();
}
