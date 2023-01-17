package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile,Long> {
    Profile findAllByNickname(String nickname);

}
