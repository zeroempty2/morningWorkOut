package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long productId);

    @Override
    void deleteById(Long productId);

    List<Product> findAllByUserId(Long userId);
    Page<Product> findAllByUserId(Long userId, Pageable pageable);
}
