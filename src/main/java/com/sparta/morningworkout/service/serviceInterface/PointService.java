package com.sparta.morningworkout.service.serviceInterface;

import com.sparta.morningworkout.dto.Point.AdminGivePresentRequest;
import com.sparta.morningworkout.dto.Point.AdminGivePresentResponse;
import com.sparta.morningworkout.dto.Point.PointResponse;
import com.sparta.morningworkout.entity.User;
import com.sparta.morningworkout.security.UserDetailsImpl;

public interface PointService {
    PointResponse getPoint(String username);
    PointResponse pointBuyGoods(String username, Long produckId);
    AdminGivePresentResponse adminGivePresent(AdminGivePresentRequest request, User user);
}
