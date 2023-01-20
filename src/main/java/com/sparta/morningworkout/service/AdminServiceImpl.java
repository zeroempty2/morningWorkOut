package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.admin.SearchNicknameResponseDto;
import com.sparta.morningworkout.dto.admin.SellerRegistResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.dto.admin.UserListResponseDto;
import com.sparta.morningworkout.entity.*;
import com.sparta.morningworkout.repository.ProfileRepository;
import com.sparta.morningworkout.repository.SellerRegistRepository;
import com.sparta.morningworkout.repository.UserRepository;
import com.sparta.morningworkout.service.serviceInterface.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final UserRepository userRepository;
    private final SellerRegistRepository sellerRegistRepository;
    private final ProfileRepository profileRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<UserListResponseDto> showCustomerList(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> customers = userRepository.findAllByRoleOrderByIdDesc(UserRoleEnum.CUSTOMER,pageable);
        return customers.map(UserListResponseDto::new);
    }

    @Override
    public Page<SearchNicknameResponseDto> showCustomerListBySearchingNickname(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findCustomersByProfileNicknameKeyword(pageable,keyword);
    }
    @Override
    public Page<SearchNicknameResponseDto> showSellerListBySearchingNickname(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findSellersByProfileNicknameKeyword(pageable,keyword);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UserListResponseDto> showSellerList(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<User> sellers = userRepository.findAllByRoleOrderByIdDesc(UserRoleEnum.SELLER,pageable);
        return sellers.map(UserListResponseDto::new);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SellerRegistResponseDto> showSellerRegistList() {
       List<SellerRegist> sellerRegists = sellerRegistRepository.findAll();
       return sellerRegists.stream().map(SellerRegistResponseDto::new).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public StatusResponseDto acceptSellerRegist(Long authorizationRequestId) {
        SellerRegist sellerRegist = sellerRegistRepository.findById(authorizationRequestId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        long userId = sellerRegist.getUserId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        Profile profile = profileRepository.findById(user.getId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        user.changeSeller();
        profile.authorizationProfileUpdate(sellerRegist);
        sellerRegistRepository.deleteById(authorizationRequestId);
        return new StatusResponseDto(200,"판매자 승인이 완료되었습니다");
    }

    @Override
    @Transactional
    public StatusResponseDto deleteSellerRegist(Long authorizationRequestId) {
        long userId = sellerRegistRepository.findById(authorizationRequestId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        ).getUserId();
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        user.changeCustomer();
        return new StatusResponseDto(200,"판매자 취소가 완료되었습니다");
    }
}
