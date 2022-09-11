package com.sinanmutlu.vendingmachine.exception;

public class ProductException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 2L;

    private ErrorCode errorCode;

    public ProductException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}