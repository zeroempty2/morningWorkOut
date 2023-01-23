package com.sparta.morningworkout.repository;


import com.sparta.morningworkout.dto.search.CustomerListBySellerDto;
import com.sparta.morningworkout.entity.CustomerRequestList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRequestListRepository extends JpaRepository<CustomerRequestList,Long> {

    CustomerRequestList findByProductId(Long productId);
    Page<CustomerRequestList> findAllBySellerId(Long sellerId, Pageable pageable);

    CustomerRequestList findByUserId(Long userId);

    @Query(value = "SELECT new com.sparta.morningworkout.dto.search.CustomerListBySellerDto(u.username,p.nickname) " +
            "from Profile p join users u on p.id = u.id join CustomerRequestList c on c.userId = u.id where u.username like %:keyword% AND c.sellerId=:sellerId")
    Page<CustomerListBySellerDto> findAllByCustomerName(long sellerId, String keyword, Pageable pageable);
}
