package com.sinanmutlu.vendingmachine.service;

import com.sinanmutlu.vendingmachine.dto.ProductDto;
import com.sinanmutlu.vendingmachine.dto.ProductReqDto;
import com.sinanmutlu.vendingmachine.entity.Product;
import com.sinanmutlu.vendingmachine.exception.ErrorCode;
import com.sinanmutlu.vendingmachine.exception.ProductException;
import com.sinanmutlu.vendingmachine.exception.UserException;
import com.sinanmutlu.vendingmachine.mapper.ProductMapper;
import com.sinanmutlu.vendingmachine.repository.ProductRepository;
import com.sinanmutlu.vendingmachine.repository.RoleRepository;
import com.sinanmutlu.vendingmachine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductMapper productMapper;
    @Override
    public List<ProductDto> getAllProducts() {
        return productMapper.toDtos(productRepository.findAll());
    }

    @Override
    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(() -> new UserException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @Override
    public void updateProduct(Product product) {
        productRepository.save(product);

    }

    @Override
    public ProductDto addProduct(ProductReqDto productReqDto) {

        //Assumed productAmount can be 0.

        if(productReqDto.getCost() % 5 != 0){
            throw new ProductException(ErrorCode.INVALID_PRODUCT_COST);
        }

        userRepository.findByIdAndRoles(productReqDto.getSellerId(), "SELLER").orElseThrow(() -> new ProductException(ErrorCode.SELLER_NOT_FOUND));

        Optional<Product> products = productRepository.findByProductName(productReqDto.getProductName());

        if (products.isPresent()){
            throw new ProductException(ErrorCode.PRODUCT_ALREADY_EXIST);
        }

        Product product = productMapper.toEntity(productReqDto);

        return productMapper.toDto(productRepository.save(product));

    }

    @Override
    public void removeProduct(Long productId) {

        Product products =  productRepository.findById(productId).orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        productRepository.delete(products);

    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {

        Product product =  productRepository.findById(productDto.getId()).orElseThrow(() -> new ProductException(ErrorCode.PRODUCT_NOT_FOUND));

        if (product.getProductName() != productDto.getProductName()){
           Optional<Product> products = productRepository.findByProductName(productDto.getProductName());
           //Assumed productName is unique
           if (products.isPresent()){
               throw new ProductException(ErrorCode.INVALID_PRODUCT_NAME);
           }
        }
        // Assumed seller of product can not be changed
        if (product.getSellerId() != productDto.getSellerId()){
            throw new ProductException(ErrorCode.INVALID_SELLERID);
        }

        product = productMapper.toEntity(productDto);

        return productMapper.toDto(productRepository.save(product));
    }
}
