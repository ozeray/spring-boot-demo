package com.it.demo.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class ProductDto {

    private Long id;

    @NotEmpty
    private String name;

    @NotEmpty private String owner;

    @NotNull
    private BigDecimal price;
}
