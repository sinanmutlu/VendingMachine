package com.sinanmutlu.vendingmachine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sinanmutlu.vendingmachine.dto.ProductDto;
import com.sinanmutlu.vendingmachine.dto.ProductReqDto;
import com.sinanmutlu.vendingmachine.entity.Product;
import com.sinanmutlu.vendingmachine.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

class ProductControllerTest {

    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private Product product1;
    private Product product2;
    private ProductDto productDto1;
    private ProductDto productDto2;
    private ProductDto product;
    private ProductReqDto productReqDto;

    private List<Product> productList = new ArrayList<>();
    private List<ProductDto> productDtoList = new ArrayList<>();
    private final long SELLER_ID = 1L;

    @BeforeEach
    void setUp() {
        product1 = Product.builder().productName("water").amountAvailable(10).cost(20).sellerId(SELLER_ID).id(1L).build();
        product2 = Product.builder().productName("su").amountAvailable(10).cost(10).sellerId(SELLER_ID).id(2L).build();
        productDto1 = ProductDto.builder().productName("water").amountAvailable(10).cost(20).sellerId(SELLER_ID).id(1L).build();
        productDto2 = ProductDto.builder().productName("su").amountAvailable(10).cost(10).sellerId(SELLER_ID).id(2L).build();
        productReqDto = ProductReqDto.builder()
                .productName("product3")
                .amountAvailable(50)
                .cost(50)
                .sellerId(1L)
                .build();
        product = ProductDto.builder()
                .id(3L)
                .productName("product3")
                .amountAvailable(50)
                .cost(50)
                .sellerId(1L)
                .build();
        productList.addAll(List.of(product1,product2));
        productDtoList.addAll(List.of(productDto1,productDto2));

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @AfterEach
    void tearDown() {
        product1 = product2 = null;
        productList = null;
        productDtoList = null;
        //product = null;
        productReqDto = null;
    }

    @Test
    void getAllProducts() throws Exception{
        Mockito.when(productService.getAllProducts()).thenReturn(productDtoList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/products/all")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].productName", is("su")));
    }

    @Test
    void addProduct() throws Exception{

        Mockito.when(productService.addProduct(productReqDto)).thenReturn(product);

        String content = objectWriter.writeValueAsString(productReqDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content);

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.productName", is("product3")));
    }
}