package com.sparta.morningworkout.dto.Point;

import com.sparta.morningworkout.entity.Point;
import lombok.Getter;

@Getter
public class AdminGivePresentResponse {
    private String adminName;
    private String userName;
    private int point;

    public AdminGivePresentResponse(Point point) {
        this.adminName = point.getAdminname();
        this.userName = point.getUsername();
        this.point = point.getPoint();
    }
}
