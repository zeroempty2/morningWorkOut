package com.sparta.morningworkout.repository;

import com.sparta.morningworkout.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
