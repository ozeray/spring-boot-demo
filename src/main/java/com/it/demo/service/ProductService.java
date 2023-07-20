package com.it.demo.service;

import com.it.demo.dto.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    ProductDto createProduct(ProductDto product);
    ProductDto updateProduct(ProductDto product);
    ProductDto getProductById(Long productId);
    List<ProductDto> getAllProducts();
    List<ProductDto> getProductsByMinPrice(BigDecimal min);
    List<ProductDto> getProductsByMaxPrice(BigDecimal max);
    List<ProductDto> getProductsByOwner(String  owner);
    void deleteProduct(Long productId);
}
