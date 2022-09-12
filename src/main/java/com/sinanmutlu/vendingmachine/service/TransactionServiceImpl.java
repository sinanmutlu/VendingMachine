package com.sinanmutlu.vendingmachine.service;


import com.sinanmutlu.vendingmachine.dto.*;
import com.sinanmutlu.vendingmachine.entity.Product;
import com.sinanmutlu.vendingmachine.entity.Transaction;
import com.sinanmutlu.vendingmachine.entity.UserEnt;
import com.sinanmutlu.vendingmachine.exception.ErrorCode;
import com.sinanmutlu.vendingmachine.exception.ProductException;
import com.sinanmutlu.vendingmachine.exception.TransactionException;
import com.sinanmutlu.vendingmachine.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final ProductService productService;

    private final UserService userService;

    private final TransactionRepository transactionRepository;

    @Override
    public DepositResDto deposit(DepositReqDto depositReqDto) {

        logger.info("Adding deposit " + depositReqDto.toString());

        ArrayList<String> coinTypes = new ArrayList<>();

        coinTypes.add("5");
        coinTypes.add("10");
        coinTypes.add("20");
        coinTypes.add("50");
        coinTypes.add("100");

        UserEnt user = userService.getUser(depositReqDto.getUserId());

        if (!coinTypes.contains(depositReqDto.getCoinType())){
            throw new TransactionException(ErrorCode.INVALID_COIN_TYPE);
        }

        user.setDeposit(user.getDeposit() + (Integer.parseInt(depositReqDto.getCoinType())));

        userService.updateUser(user);

        logger.info("User: " + user.getUsername() + " new balance: " + user.getDeposit());

        Transaction transaction = new Transaction();
        transaction.setUserId(user.getId());
        transaction.setTransactionType("DEPOSIT");
        transaction.setTransactionAmount((Integer.parseInt(depositReqDto.getCoinType())));

        //transaction =
        transactionRepository.save(transaction);

        logger.info("Transaction Created: " + transaction);



        return new DepositResDto(user.getId(), user.getDeposit());

    }

    @Override
    public BuyResDto buy(BuyReqDto buyReqDto) {

        ArrayList<String> returnTypes = new ArrayList<>();

        returnTypes.add("100");
        returnTypes.add("50");
        returnTypes.add("20");
        returnTypes.add("10");
        returnTypes.add("5");

        UserEnt user = userService.getUser(buyReqDto.getUserId());

        if (!user.getRoles().contains("BUYER")){
            throw new ProductException(ErrorCode.BUYER_NOT_FOUND);
        }

        Product product = productService.getProduct(buyReqDto.getProductId());

        Map<String,Integer> changes = new HashMap<String, Integer>();

        int price = product.getCost() * buyReqDto.getNumberOfProducts();

        int change = user.getDeposit();

        if (price > user.getDeposit()){
            throw new TransactionException(ErrorCode.NOT_ENOUGH_DEPOSIT);
        }

        product.setAmountAvailable(product.getAmountAvailable() - buyReqDto.getNumberOfProducts());

        user.setDeposit(0);

        change = change - price;

        // minimum number of coin as change and assumed we have enough for all coins to return as change
        for (String coinType:returnTypes){
            int coin = Integer.parseInt(coinType);
            if(change > coin){
                changes.put(coinType, change / coin);
                change = change % coin;
            }
        }

        userService.updateUser(user);
        productService.updateProduct(product);

        BuyResDto buyResDto = new BuyResDto(buyReqDto.getUserId(), buyReqDto.getProductId(), buyReqDto.getNumberOfProducts(), price, changes);

        logger.info("Buy details: " + buyResDto);

        return buyResDto;
    }

    @Override
    public DepositResDto resetBalance(Long userId) {

        UserEnt user = userService.getUser(userId);

        if (!user.getRoles().contains("BUYER")){
            throw new ProductException(ErrorCode.BUYER_NOT_FOUND);
        }

        user.setDeposit(0);

        userService.updateUser(user);

        logger.info("User: " + user.getId() + " deposit: " + user.getDeposit());

        return new DepositResDto(user.getId(),user.getDeposit());
    }
}
