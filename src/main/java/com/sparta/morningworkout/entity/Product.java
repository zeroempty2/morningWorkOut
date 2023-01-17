package com.sparta.morningworkout.entity;

import com.sparta.morningworkout.dto.product.ProductUpdateRequestDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@Entity
@SuperBuilder
@NoArgsConstructor
public class Product extends TimeStamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private long userId;
    @Column(nullable = false)
    private String productName;
    @Column(nullable = false)
    private int price;
    @Column
    @Enumerated(EnumType.STRING)
    private CategoryEnum category;

    public void update(ProductUpdateRequestDto productUpdateRequestDto) {
        this.price = productUpdateRequestDto.getPrice();
    }
}
