package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.SellerRegist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRegistRepository extends JpaRepository<SellerRegist, Long> {
    SellerRegist findById(long sellerRegistId);
}
