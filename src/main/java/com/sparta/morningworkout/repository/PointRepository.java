package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<Point, Long> {
    Point findByUsername(String username);
}
