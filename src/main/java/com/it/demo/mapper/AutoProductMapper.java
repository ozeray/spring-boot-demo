package com.it.demo.mapper;

import com.it.demo.dto.ProductDto;
import com.it.demo.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoProductMapper {
    AutoProductMapper MAPPER = Mappers.getMapper(AutoProductMapper.class);

    ProductDto mapToProductDto(Product product);
    Product mapToProduct(ProductDto product);
}
