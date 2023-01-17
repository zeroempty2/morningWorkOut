package com.sparta.morningworkout.repository;


import com.sparta.morningworkout.entity.CustomerRequestList;
import com.sparta.morningworkout.entity.SellerRegistList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestListRepository extends JpaRepository<CustomerRequestList,Long> {

    SellerRegistList findByProductId(Long productId);
}
