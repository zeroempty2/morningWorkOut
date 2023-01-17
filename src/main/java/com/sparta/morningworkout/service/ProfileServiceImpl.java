package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.ShowCustomerProfileResponse;
import com.sparta.morningworkout.dto.ShowSellerProfileResponse;
import com.sparta.morningworkout.dto.UpdateCustomerProfileRequest;
import com.sparta.morningworkout.dto.UpdateSellerProfileRequest;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;

    @Override
    public void updateCustomerProfile(long id, UpdateCustomerProfileRequest request) {
        Profile profile = profileRepository.findById(id).orElseThrow(

                () -> new RuntimeException("수정하려는 프로필이 없습니다.")
        );
        profile.customerUpdate(request);
    }

    @Override
    public void updateSellerProfile(long id, UpdateSellerProfileRequest request) {
        Profile profile = profileRepository.findById(id).orElseThrow(

                () -> new RuntimeException("수정하려는 프로필이 없습니다.")
        );
        profile.sellerUpdate(request);
    }

    @Override
    @Transactional(readOnly = true)
    public ShowCustomerProfileResponse showCustomerProfile(String nickname) {
        Profile profiles = profileRepository.findAllByNickname(nickname);
        return new ShowCustomerProfileResponse(profiles);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShowSellerProfileResponse> showSellerProfile() {
        List<Profile> profiles = profileRepository.findAll();
        List<ShowSellerProfileResponse> profileList = new ArrayList<>();
        for (Profile profile : profiles) {
            profileList.add(new ShowSellerProfileResponse(profile));
        }
        return profileList;
    }
}
