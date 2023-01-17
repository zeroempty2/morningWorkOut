package com.sparta.morningworkout.repository;


import com.sparta.morningworkout.entity.CustomerRequestList;
import com.sparta.morningworkout.entity.SellerRegist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestListRepository extends JpaRepository<CustomerRequestList,Long> {

    SellerRegist findByProductId(Long productId);
}
