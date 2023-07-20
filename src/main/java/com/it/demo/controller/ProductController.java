package com.it.demo.controller;

import com.it.demo.dto.ProductDto;
import com.it.demo.service.ProductService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getAllProducts());
    }

    @GetMapping("/min-price/{price}")
    public ResponseEntity<List<ProductDto>> getProductsByMinPrice(@PathVariable("price")  BigDecimal price) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByMinPrice(price));
    }

    @GetMapping("/max-price/{price}")
    public ResponseEntity<List<ProductDto>> getProductsByMaxPrice(@PathVariable("price")  BigDecimal price) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByMaxPrice(price));
    }

    @GetMapping("/owner/{owner}")
    public ResponseEntity<List<ProductDto>> getProductsByOwner(@PathVariable("owner")  String owner) {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByOwner(owner));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(productService.getProductById(productId));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto productDto) {
        ProductDto savedProduct = productService.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable("id") Long productId,
                                                    @Valid @RequestBody ProductDto productDto) {
        productDto.setId(productId);
        ProductDto savedProduct = productService.updateProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body("Product successfully deleted!");
    }
}
