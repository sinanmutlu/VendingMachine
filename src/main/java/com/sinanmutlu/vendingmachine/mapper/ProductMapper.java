package com.sinanmutlu.vendingmachine.mapper;


import com.sinanmutlu.vendingmachine.dto.ProductDto;
import com.sinanmutlu.vendingmachine.dto.ProductReqDto;
import com.sinanmutlu.vendingmachine.entity.Product;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", builder = @Builder(disableBuilder = true))
public interface ProductMapper {

    ProductDto toDto(Product product);

    ProductReqDto toReqDto(Product product);
    Product toEntity(ProductDto productDto);
    Product toEntity(ProductReqDto productReqDto);
    List<ProductDto> toDtos(List<Product> productList);
}