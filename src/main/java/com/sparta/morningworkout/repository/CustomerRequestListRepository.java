package com.sparta.morningworkout.repository;


import com.sparta.morningworkout.dto.customer.CustomerRequestResponseDto;
import com.sparta.morningworkout.dto.search.CustomerListBySellerDto;
import com.sparta.morningworkout.entity.CustomerRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRequestListRepository extends JpaRepository<CustomerRequest,Long> {

    CustomerRequest findByProductId(Long productId);
    @Query("select new com.sparta.morningworkout.dto.customer.CustomerRequestResponseDto(c.id,c.productId,c.userId,p.nickname,c.isAccepted) " +
            "from CustomerRequest c left join Profile p on p.id = c.userId where c.sellerId = :sellerId")
    Page<CustomerRequestResponseDto> findAllCustomersBySellerId(long sellerId, Pageable pageable);

    CustomerRequest findByUserId(Long userId);

    @Query(value = "SELECT new com.sparta.morningworkout.dto.search.CustomerListBySellerDto(u.username,p.nickname) " +
            "from Profile p join users u on p.id = u.id join CustomerRequest c on c.userId = u.id where u.username like %:keyword% AND c.sellerId=:sellerId")
    Page<CustomerListBySellerDto> findAllByCustomerName(long sellerId, String keyword, Pageable pageable);
}
