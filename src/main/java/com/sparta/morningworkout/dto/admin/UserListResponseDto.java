package com.sparta.morningworkout.dto.admin;

import com.sparta.morningworkout.entity.User;
import lombok.Getter;

@Getter
public class UserListResponseDto {
    private String username;

    public UserListResponseDto(User user) {
        this.username = user.getUsername();
    }
}
