package com.sparta.morningworkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.morningworkout.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
	Profile findByNickname(String nickname);
}
