package com.it.demo.service.impl;

import com.it.demo.dto.ProductDto;
import com.it.demo.entity.Product;
import com.it.demo.exception.ProductAlreadyExistsException;
import com.it.demo.exception.ResourceNotFoundException;
import com.it.demo.mapper.AutoProductMapper;
import com.it.demo.repo.ProductRepository;
import com.it.demo.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto product) {
        productRepository.findByName(product.getName())
                .ifPresent(p -> {throw new ProductAlreadyExistsException();});

        Product p = AutoProductMapper.MAPPER.mapToProduct(product);
        Product savedProduct = productRepository.save(p);
        return AutoProductMapper.MAPPER.mapToProductDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto product) {
        Product existingProduct = productRepository.findById(product.getId())
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", product.getId()));
        existingProduct.setName(product.getName());
        existingProduct.setOwner(product.getOwner());
        existingProduct.setPrice(product.getPrice());
        Product updatedProduct = productRepository.save(existingProduct);
        return AutoProductMapper.MAPPER.mapToProductDto(updatedProduct);
    }

    @Override
    public ProductDto getProductById(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", productId));
        return AutoProductMapper.MAPPER.mapToProductDto(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(AutoProductMapper.MAPPER::mapToProductDto)
                .toList();
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("product", "id", productId));
        productRepository.deleteById(productId);
    }

    @Override
    public List<ProductDto> getProductsByOwner(String owner) {
        return productRepository.findByOwner(owner).stream()
                .map(AutoProductMapper.MAPPER::mapToProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> getProductsByMinPrice(BigDecimal min) {
        return productRepository.findByPriceGreaterThanEqual(min).stream()
                .map(AutoProductMapper.MAPPER::mapToProductDto)
                .toList();
    }

    @Override
    public List<ProductDto> getProductsByMaxPrice(BigDecimal max) {
        return productRepository.findByPriceLessThanEqual(max).stream()
                .map(AutoProductMapper.MAPPER::mapToProductDto)
                .toList();
    }
}
