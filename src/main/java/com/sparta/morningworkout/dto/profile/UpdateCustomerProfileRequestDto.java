package com.sparta.morningworkout.dto.profile;

import com.sparta.morningworkout.entity.CategoryEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCustomerProfileRequestDto {
    private String nickname;
    private String infoContent = null;
    private CategoryEnum category = null;

}
