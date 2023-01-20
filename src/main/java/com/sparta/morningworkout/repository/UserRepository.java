package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Page<User> findAllByRoleOrderByIdDesc(UserRoleEnum role, Pageable pageable);
    Optional<User> findByUsername(String username);

    List<User> findByUsernameContaining(String username);
}
