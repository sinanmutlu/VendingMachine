package com.sinanmutlu.vendingmachine.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {

    INTERNAL_SERVER_ERROR(800, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR),
    PRODUCT_ALREADY_EXIST(801, "Product already exist", HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT_COST(802, "Cost should be multiples of 5", HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT_AMOUNT(803, "Amount should be bigger than 0", HttpStatus.BAD_REQUEST),

    SELLER_NOT_FOUND(805, "Seller was not found", HttpStatus.BAD_REQUEST),
    PRODUCT_NOT_FOUND(806, "Product was not found", HttpStatus.BAD_REQUEST),
    INVALID_PRODUCT_NAME(807, "Product name is in use", HttpStatus.BAD_REQUEST),

    INVALID_SELLERID(808, "SellerId can not be changed", HttpStatus.BAD_REQUEST),
    USER_ALREADY_EXIST(901, "User already exist", HttpStatus.BAD_REQUEST),
    INVALID_DEPOSIT(902, "Deposit should be multiples of 5", HttpStatus.BAD_REQUEST),
    INVALID_ROLE(902, "Role should be seller or buyer", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(903, "User was not found", HttpStatus.BAD_REQUEST),
    INVALID_COIN_TYPE(701, "Coin type should be one of (5, 10, 20, 50, 100)", HttpStatus.BAD_REQUEST),
    INVALID_COIN_NUMBER(702, "Coin number should be more than 0", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_DEPOSIT(703, "Not enough balance", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS(601, "Username or password is invalid", HttpStatus.BAD_REQUEST);

    private ErrorCode(int code, String msg, HttpStatus httpStatus) {
        this.errorCode = code;
        this.errorMsg = msg;
        this.httpStatus = httpStatus;
    }

    private int errorCode;
    private String errorMsg;
    private HttpStatus httpStatus;

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
