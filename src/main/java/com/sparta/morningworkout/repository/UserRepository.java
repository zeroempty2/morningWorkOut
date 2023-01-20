package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.dto.admin.SearchNicknameResponseDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByRoleOrderByIdDesc(UserRoleEnum role, Pageable pageable);
    Optional<User> findByUsername(String username);
    @Query("select new com.sparta.morningworkout.dto.admin.SearchNicknameResponseDto(u.username, u.profile.nickname) " +
                "from users u " +
                "where u.role = 'CUSTOMER' and u.profile.nickname " +
                "like %:keyword%")
//        연관관계 있으면 찾아오는 연관관계의 테이블에 자동으로 join 쿼리 날림 위 쿼리는 밑의 쿼리와 같음
//    @Query("select new com.sparta.morningworkout.dto.admin.SearchNicknameResponseDto(u.username, p.nickname) " +
//            "from users u " +
//            "join u.profile p " +
//            "where u.role = 'SELLER' and p.nickname " +
//            "like %:keyword%")
    Page<SearchNicknameResponseDto> findCustomersByProfileNicknameKeyword(Pageable pageable, @Param("keyword")String keyword);

    @Query("select new com.sparta.morningworkout.dto.admin.SearchNicknameResponseDto(u.username, u.profile.nickname) " +
            "from users u " +
            "where u.role = 'SELLER' and u.profile.nickname " +
            "like %:keyword%")
    Page<SearchNicknameResponseDto> findSellersByProfileNicknameKeyword(Pageable pageable, @Param("keyword")String keyword);
}
