package com.it.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @GeneratedValue
    @Id
    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty
    private String owner;

    @NotNull
    private BigDecimal price;

    @PrePersist
    @PreUpdate
    public void setPricePrecision() {
        price.setScale(2, RoundingMode.HALF_UP);
    }
}
