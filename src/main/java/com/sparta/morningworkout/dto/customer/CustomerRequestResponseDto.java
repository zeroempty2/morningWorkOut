package com.sparta.morningworkout.dto.customer;

import lombok.Getter;

@Getter
public class CustomerRequestResponseDto {
    private long customerRequestId;
    private long productId;
    private long customerId;
    private String customerNickName;
    private boolean isAccepted;

    public CustomerRequestResponseDto(long customerRequestId, long productId, long customerId, String customerNickName, boolean isAccepted) {
        this.customerRequestId = customerRequestId;
        this.productId = productId;
        this.customerId = customerId;
        this.customerNickName = customerNickName;
        this.isAccepted = isAccepted;
    }
}
