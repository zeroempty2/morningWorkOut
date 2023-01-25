package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.Point.PointUpdateRequestDto;
import com.sparta.morningworkout.dto.admin.UserContentsResponseDto;
import com.sparta.morningworkout.dto.admin.SellerRegistResponseDto;
import com.sparta.morningworkout.dto.StatusResponseDto;
import com.sparta.morningworkout.entity.*;
import com.sparta.morningworkout.repository.PointRepository;
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
    private final PointRepository pointRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<UserContentsResponseDto> showCustomerList(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllByRoleOrderByIdDescQuery(UserRoleEnum.CUSTOMER,pageable);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<UserContentsResponseDto> showSellerList(int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findAllByRoleOrderByIdDescQuery(UserRoleEnum.SELLER,pageable);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<UserContentsResponseDto> showCustomerListBySearchingNickname(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findCustomersByProfileNicknameKeyword(pageable,keyword);
    }
    @Override
    @Transactional(readOnly = true)
    public Page<UserContentsResponseDto> showSellerListBySearchingNickname(int page, int size, String keyword) {
        Pageable pageable = PageRequest.of(page, size);
        return userRepository.findSellersByNickname(pageable,keyword);
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
    public StatusResponseDto deleteSellerRegist(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않습니다.")
        );
        user.changeCustomer();
        return new StatusResponseDto(200,"판매자 취소가 완료되었습니다");
    }
    @Override
    public StatusResponseDto givePoint(Long userId, PointUpdateRequestDto pointUpdateDto) {
        Point point = pointRepository.findById(userId).orElseThrow(
                () -> new IllegalArgumentException("찾는 유저가 없습니다.")
        );
        point.plusPoint(pointUpdateDto.getGivePoint());
        pointRepository.save(point);
        return new StatusResponseDto(200,"포인트 지급이 완료되었습니다");
    }

}
