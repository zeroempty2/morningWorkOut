package com.sparta.morningworkout.service;

import com.sparta.morningworkout.dto.PageDto;
import com.sparta.morningworkout.dto.ProductResponseDto;
import com.sparta.morningworkout.entity.Product;
import com.sparta.morningworkout.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void addProduct() {

    }

    @Override
    public void updateProduct() {

    }

    @Override
    public void deleteProduct() {

    }

    @Override
    public List<ProductResponseDto> showProductList() {
        List<ProductResponseDto> products = productRepository.findAll().stream().map(ProductResponseDto::new).toList();
        return products;
    }

    @Override
    public void showProductBySeller() {

    }
}
