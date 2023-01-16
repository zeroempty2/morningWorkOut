package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
