package com.sinanmutlu.vendingmachine.service;

import com.sinanmutlu.vendingmachine.dto.ProductDto;
import com.sinanmutlu.vendingmachine.dto.ProductReqDto;
import com.sinanmutlu.vendingmachine.entity.Product;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();

    Product getProduct(Long productId);

    void updateProduct(Product product);

    ProductDto addProduct(ProductReqDto productReqDto);

    void removeProduct(Long productId);

    ProductDto updateProduct(ProductDto productDto);
}
