package com.sinanmutlu.vendingmachine.controller;

import com.sinanmutlu.vendingmachine.dto.ProductDto;
import com.sinanmutlu.vendingmachine.dto.ProductReqDto;
import com.sinanmutlu.vendingmachine.service.ProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        logger.info("Getting all products");

        return ResponseEntity.ok(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductDto> addProduct(@Validated @RequestBody ProductReqDto productReqDto) {

        logger.info("Adding new product with  " + productReqDto.toString() );
        return new ResponseEntity<ProductDto>(productService.addProduct(productReqDto), HttpStatus.OK);

    }

    @PutMapping("/update")
    public ResponseEntity<ProductDto> updateProduct(@Validated @RequestBody ProductDto productDto) {

        logger.info("Update product " );
        return new ResponseEntity<ProductDto>(productService.updateProduct(productDto), HttpStatus.OK);

    }

    @DeleteMapping("/remove")
    public ResponseEntity<Long> removeProduct(@RequestParam Long productId) {

        productService.removeProduct(productId);

        return new ResponseEntity<>(productId, HttpStatus.OK);
    }
}
