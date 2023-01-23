package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PointRepository extends JpaRepository<Point, Long> {
    Optional<Point> findByUsername(String username);

}
