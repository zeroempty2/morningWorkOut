package com.sparta.morningworkout.entity;

import lombok.Getter;

public enum CategoryEnum {
    IT("it"),
    FASHION("fashion"),
    FOOD("food"),
    TOOLS("tools");
    @Getter
    private final String value;

    CategoryEnum(String value) {
        this.value = value;
    }
}
