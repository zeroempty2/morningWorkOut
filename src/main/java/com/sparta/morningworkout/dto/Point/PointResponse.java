package com.sparta.morningworkout.dto.Point;

import com.sparta.morningworkout.entity.Point;
import lombok.Getter;

@Getter
public class PointResponse {
    private String username;
    private int point;

    public PointResponse(Point point) {
        this.username = point.getUsername();
        this.point = point.getPoint();
    }
}
