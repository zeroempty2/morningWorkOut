package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.profile.ShowCustomerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.ShowSellerProfileResponseDto;
import com.sparta.morningworkout.dto.profile.UpdateCustomerProfileRequestDto;
import com.sparta.morningworkout.dto.profile.UpdateSellerProfileRequestDto;
import com.sparta.morningworkout.entity.Profile;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.service.serviceInterface.ProfileService;
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
    public void updateCustomerProfile(long id, UpdateCustomerProfileRequestDto request) {
        Profile profile = profileRepository.findById(id).orElseThrow(

                () -> new RuntimeException("수정하려는 프로필이 없습니다.")
        );
        profile.customerUpdate(request);
    }

    @Override
    public void updateSellerProfile(long id, UpdateSellerProfileRequestDto request) {
        Profile profile = profileRepository.findById(id).orElseThrow(

                () -> new RuntimeException("수정하려는 프로필이 없습니다.")
        );
        profile.sellerUpdate(request);
    }

    @Override
    @Transactional(readOnly = true)
    public ShowCustomerProfileResponseDto showCustomerProfile(String nickname) {
        Profile profiles = profileRepository.findAllByNickname(nickname);
        return new ShowCustomerProfileResponseDto(profiles);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ShowSellerProfileResponseDto> showSellerProfile() {
        List<Profile> profiles = profileRepository.findAll();
        List<ShowSellerProfileResponseDto> profileList = new ArrayList<>();
        for (Profile profile : profiles) {
            profileList.add(new ShowSellerProfileResponseDto(profile));
        }
        return profileList;
    }
}
