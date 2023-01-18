package com.sparta.morningworkout.repository;


import com.sparta.morningworkout.dto.customer.CustomerListResponseDto;
import com.sparta.morningworkout.dto.sellers.SellerListResponseDto;
import com.sparta.morningworkout.entity.CustomerRequestList;
import com.sparta.morningworkout.entity.SellerRegist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRequestListRepository extends JpaRepository<CustomerRequestList,Long> {

    SellerRegist findByProductId(Long productId);
    Page<CustomerRequestList> findAllBySellerId(Long sellerId, Pageable pageable);

    CustomerRequestList findByUserId(Long userId);
}
