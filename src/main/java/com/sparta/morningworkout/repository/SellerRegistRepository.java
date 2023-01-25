package com.sparta.morningworkout.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sparta.morningworkout.entity.CategoryEnum;
import com.sparta.morningworkout.entity.SellerRegist;

public interface SellerRegistRepository extends JpaRepository<SellerRegist, Long> {
    SellerRegist findById(long sellerRegistId);

    SellerRegist findByInfoContentAndCategory(String infoContent, CategoryEnum category);
}
