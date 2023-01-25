package com.sparta.morningworkout.annotation;

import com.sparta.morningworkout.controller.WithMockCustomUserSecurityContextFactory;
import com.sparta.morningworkout.entity.UserRoleEnum;
import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = WithMockCustomUserSecurityContextFactory.class)
public @interface WithMockCustomUser {
    String username() default "test";

    String password() default "name";

    UserRoleEnum role() default UserRoleEnum.CUSTOMER;

}
