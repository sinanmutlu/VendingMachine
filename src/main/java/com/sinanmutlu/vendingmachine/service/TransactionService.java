package com.sinanmutlu.vendingmachine.service;


import com.sinanmutlu.vendingmachine.dto.*;

public interface TransactionService {


    DepositResDto deposit(DepositReqDto depositReqDto);

    BuyResDto buy(BuyReqDto buyReqDto);

    DepositResDto resetBalance(Long userId);
}
