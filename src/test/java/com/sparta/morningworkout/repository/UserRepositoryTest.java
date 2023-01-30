package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.dto.admin.UserContentsResponseDto;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.entity.UserRoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.Option;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAllByRoleOrderByIdDesc() {

        //given
        User user = new User("sdad123","asd12345!", UserRoleEnum.CUSTOMER);

        Pageable pageable = PageRequest.of(1,5);

        //when
        User savedUser = userRepository.save(user);
        Page<User> foundUser = userRepository.findAllByRoleOrderByIdDesc(UserRoleEnum.CUSTOMER,pageable);
        //then
        assertThat(foundUser).isNotNull();
    }

    @Test
    void findAllByRoleOrderByIdDescQuery() {

        User user = new User("sdad123","asd12345!", UserRoleEnum.CUSTOMER);

        Pageable pageable = PageRequest.of(1,5);

        //when
        User savedUser = userRepository.save(user);
        Page<UserContentsResponseDto> foundUser = userRepository.findAllByRoleOrderByIdDescQuery(UserRoleEnum.CUSTOMER,pageable);
        //then
        assertThat(foundUser).isNotNull();

    }

    @Test
    void findByUsername() {

        //given
        User user = new User("sdad123","asd12345!", UserRoleEnum.CUSTOMER);
        //when
        User savedUser = userRepository.save(user);
        Optional<User> foundUser = userRepository.findByUsername(user.getUsername());
        //then

        assertThat(foundUser).isPresent();

    }
    @Test
    void addUser(){
        //given
        User user = new User("sdad123","asd12345!", UserRoleEnum.CUSTOMER);
        //when
        User savedUser = userRepository.save(user);
        //then

        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    void findCustomersByProfileNicknameKeyword() {
    }

    @Test
    void findSellersByNickname() {
    }
}