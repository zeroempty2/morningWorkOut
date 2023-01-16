package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.SellerRegistList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRegistRepository extends JpaRepository<SellerRegistList, Long> {
}
