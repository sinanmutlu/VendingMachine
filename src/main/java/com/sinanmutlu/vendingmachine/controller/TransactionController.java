package com.sinanmutlu.vendingmachine.controller;

import com.sinanmutlu.vendingmachine.dto.*;
import com.sinanmutlu.vendingmachine.service.TransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("transaction")
public class TransactionController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<DepositResDto> deposit(@Validated @RequestBody DepositReqDto depositReqDto) {

        logger.info("Adding deposit with  " + depositReqDto.toString() );
        return new ResponseEntity<DepositResDto>(transactionService.deposit(depositReqDto), HttpStatus.OK);

    }

    @PostMapping("/buy")
    public ResponseEntity<BuyResDto> buy(@Validated @RequestBody BuyReqDto buyReqDto) {

        logger.info("Buying product with  " + buyReqDto.toString() );
        return new ResponseEntity<BuyResDto>(transactionService.buy(buyReqDto), HttpStatus.OK);

    }

    @PutMapping("/reset")
    public ResponseEntity<DepositResDto> resetBalance(@RequestParam Long userId) {

        logger.info("Reset users balance" );
        return new ResponseEntity<DepositResDto>(transactionService.resetBalance(userId), HttpStatus.OK);

    }
}
