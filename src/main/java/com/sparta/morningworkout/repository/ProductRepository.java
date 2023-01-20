package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.dto.product.ProductResponseDto;
import com.sparta.morningworkout.dto.search.ProductResponseSearchByNameDto;
import com.sparta.morningworkout.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long productId);


    @Override
    void deleteById(Long productId);

    Page<Product> findAll(Pageable pageable);

    List<Product> findAllByUserId(Long userId);

    Page<Product> findAllByUserId(long userId, Pageable pageable);
//    Page<Product> findAllByUserId(List<Long> userId, Pageable pageable);

    Page<Product> findByProductNameContaining(String keyword, Pageable pageable);

    @Query("select new com.sparta.morningworkout.dto.search.ProductResponseSearchByNameDto(p.productName,p.price,u.username,p.category)" +
            " from users u LEFT join Product p on u.id = p.userId where u.role= 'SELLER' AND u.username like %:sellerName%")
    Page<ProductResponseSearchByNameDto> findAllBySellerName(String sellerName, Pageable pageable);

    @Query("select new com.sparta.morningworkout.dto.product.ProductResponseDto(p.productName,p.price,p.category) from Product p where p.userId=:sellerId AND p.productName like %:keyword%")
    Page<ProductResponseDto> findAllBySellerIdByProductName(String keyword, long sellerId, Pageable pageable);
}