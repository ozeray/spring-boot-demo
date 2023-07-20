package com.it.demo.repo;

import com.it.demo.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByName(String name);

    List<Product> findByOwner(String owner);

    List<Product> findByPriceGreaterThanEqual(BigDecimal min);

    List<Product> findByPriceLessThanEqual(BigDecimal max);
}
