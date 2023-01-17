package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByRoleOrderByIdDesc(UserRoleEnum role, Pageable pageable);
}
