package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.dto.admin.UserContentsResponseDto;
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
//    Optional<User> findByUserId(Long userId);
    @Query("select new com.sparta.morningworkout.dto.admin.UserContentsResponseDto(u.username, p.nickname) " +
            "from users u left join Profile p on u.id = p.id where u.role = :role")
    Page<UserContentsResponseDto> findAllByRoleOrderByIdDescQuery(@Param("role") UserRoleEnum role, Pageable pageable);
    Optional<User> findByUsername(String username);
//    @Query("select new com.sparta.morningworkout.dto.admin.SearchNicknameResponseDto(u.username, u.profile.nickname) " +
//                "from users u " +
//                "where u.role = 'CUSTOMER' and u.profile.nickname " +
//                "like %:keyword%")
//        연관관계 있으면 찾아오는 연관관계의 테이블에 자동으로 join 쿼리 날림 위 쿼리는 밑의 쿼리와 같음 -> (변경)밑 쿼리는 연관관계 제거 후 쿼리임
    @Query("select new com.sparta.morningworkout.dto.admin.UserContentsResponseDto(u.username, p.nickname) " +
            "from users u left join Profile p on u.id = p.id where u.role = 'CUSTOMER' and p.nickname " +
            "like %:keyword%")
    Page<UserContentsResponseDto> findCustomersByProfileNicknameKeyword(Pageable pageable, @Param("keyword")String keyword);


    @Query("select new com.sparta.morningworkout.dto.admin.UserContentsResponseDto(u.username, p.nickname) " +
            "from users u left join Profile p on u.id = p.id where u.role = 'SELLER' and p.nickname " +
            "like %:keyword%")
    Page<UserContentsResponseDto> findSellersByNickname(Pageable pageable, @Param("keyword")String keyword);

}
