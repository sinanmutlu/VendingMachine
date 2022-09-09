package com.sinanmutlu.vendingmachine.controller;


import com.sinanmutlu.vendingmachine.entity.Product;
import com.sinanmutlu.vendingmachine.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Collections;
import java.util.Random;

import static org.mockito.Matchers.any;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    MockMvc mvc;

    @MockBean
    ProductRepository repository;

    @Test
    public void testCreate() throws Exception {
        MockHttpServletRequestBuilder request = post("/products/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"amountAvailable\": 10 , \"cost\" : 10 , \"productName\": \"su\" , \"sellerId\" : 1 }");



        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName", equalTo("su")));

        verify(this.repository).save(any(Product.class));
    }

    @Test
    public void testList() throws Exception {
        Long id = new Random().nextLong();
        Product product = new Product();
        product.setProductName("water");
        product.setSellerId(1L);
        product.setCost(10);
        product.setAmountAvailable(50);
        product.setId(id);

        when(this.repository.findAll()).thenReturn(Collections.singletonList(product));

        MockHttpServletRequestBuilder request = get("/products/all")
                .contentType(MediaType.APPLICATION_JSON);

        this.mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(id) ))
                .andExpect(jsonPath("$[0].productName", equalTo("water") ));

    }
}