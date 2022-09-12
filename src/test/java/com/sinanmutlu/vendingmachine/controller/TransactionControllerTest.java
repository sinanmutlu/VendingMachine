package com.sinanmutlu.vendingmachine.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.sinanmutlu.vendingmachine.dto.*;
import com.sinanmutlu.vendingmachine.service.TransactionService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


//import static org.hamcrest.Matchers.*;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class TransactionControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    ObjectWriter objectWriter = objectMapper.writer();

    @Mock
    private TransactionService transactionService;

    @InjectMocks
    private TransactionController transactionController;

    DepositReqDto depositReqDto;
    DepositResDto depositResDto;
    BuyReqDto buyReqDto;
    BuyResDto buyResDto;
    Map<String, Integer> changes = new HashMap<>();

    @BeforeEach
    void setUp() {
        depositReqDto = DepositReqDto.builder().coinType("50").userId(1L).build();
        depositResDto = DepositResDto.builder().userId(1L).deposit(150).build();

        changes.put("50",2);
        buyReqDto = BuyReqDto.builder().numberOfProducts(2).productId(1L).userId(1L).build();
        buyResDto = BuyResDto.builder().userId(1L).productId(1L).price(100).numberOfProducts(2).change(changes).build();

        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(transactionController).build();
    }

    @AfterEach
    void tearDown() {
        depositResDto = null;
        depositReqDto = null;
        changes.clear();
        buyResDto = null;
        buyReqDto = null;
    }

    @Test
    void deposit() throws Exception{
        when(transactionService.deposit(any(DepositReqDto.class))).thenReturn(depositResDto);

        String content = objectWriter.writeValueAsString(depositReqDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/transaction/deposit")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("utf-8");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));
    }


    @Test
    void buy() throws Exception{

        when(transactionService.buy(any())).thenReturn(buyResDto);

        String content = objectWriter.writeValueAsString(buyReqDto);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/transaction/buy")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(content)
                .characterEncoding("utf-8");

        mockMvc.perform(mockRequest)
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MockMvcResultMatchers.jsonPath("$", notNullValue()));
    }

}