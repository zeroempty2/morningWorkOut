package com.sparta.morningworkout.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column
    @Enumerated(EnumType.STRING)
    private UserRoleEnum role;

    public void changeSeller(){
        this.role = UserRoleEnum.SELLER;
    }
    public void changeCustomer(){
        this.role = UserRoleEnum.CUSTOMER;
    }
}