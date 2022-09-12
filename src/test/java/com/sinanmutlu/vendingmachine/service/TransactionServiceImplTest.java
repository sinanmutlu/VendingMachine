package com.sinanmutlu.vendingmachine.service;

import com.sinanmutlu.vendingmachine.dto.*;
import com.sinanmutlu.vendingmachine.entity.Product;
import com.sinanmutlu.vendingmachine.entity.Transaction;
import com.sinanmutlu.vendingmachine.entity.UserEnt;
import com.sinanmutlu.vendingmachine.repository.TransactionRepository;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
class TransactionServiceImplTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ProductService productService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    DepositReqDto depositReqDto;
    DepositResDto depositResDto;
    BuyReqDto buyReqDto;
    BuyResDto buyResDto;
    Map<String, Integer> changes = new HashMap<>();
    UserEnt userEnt;
    Product product;
    ProductDto productDto;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.initMocks(this);

        depositReqDto = DepositReqDto.builder().coinType("50").userId(1L).build();
        depositResDto = DepositResDto.builder().userId(1L).deposit(150).build();
        changes.put("50",2);
        buyReqDto = BuyReqDto.builder().numberOfProducts(2).productId(1L).userId(1L).build();
        buyResDto = BuyResDto.builder().userId(1L).productId(1L).price(100).numberOfProducts(2).change(changes).build();
        userEnt = UserEnt.builder().id(1L).username("snn").password("mtl").deposit(200).roles("BUYER").build();
        product = Product.builder().id(1L).productName("water").cost(50).amountAvailable(27).sellerId(1L).build();
        productDto = ProductDto.builder().id(1L).productName("water").cost(50).amountAvailable(27).sellerId(1L).build();


    }

    @AfterEach
    void tearDown() {
        depositResDto = null;
        depositReqDto = null;
        changes.clear();
        buyResDto = null;
        buyReqDto = null;
        userEnt = null;
        product = null;
        productDto = null;
    }

    @Test
    void deposit() throws Exception {

        when(userService.getUser(any())).thenReturn(userEnt);

        userEnt.setDeposit(depositResDto.getDeposit());
        when(userService.updateUser(any(UserEnt.class))).thenReturn(userEnt);

        Transaction transaction = Transaction.builder()
                .id(1L).
                userId(1L)
                .transactionType("DEPOSIT")
                .transactionAmount((Integer.parseInt(depositReqDto.getCoinType())))
                .build();

        when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction);

        DepositResDto depositResDto1 = transactionService.deposit(depositReqDto);
        Assert.assertNotNull(depositResDto1);

    }

    @Test
    void buy() {
        when(userService.getUser(any())).thenReturn(userEnt);
        when(productService.getProduct(any())).thenReturn(product);

        when(userService.updateUser(any(UserEnt.class))).thenReturn(userEnt);
        when(productService.updateProduct(productDto)).thenReturn(productDto);


        BuyResDto buyResDto1 = transactionService.buy(buyReqDto);
        Assert.assertNotNull(buyResDto1);

    }
}
