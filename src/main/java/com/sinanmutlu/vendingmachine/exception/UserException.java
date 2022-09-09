package com.sinanmutlu.vendingmachine.exception;

public class UserException extends RuntimeException {


    private static final long serialVersionUID = 1L;

    private ErrorCode errorCode;

    public UserException(ErrorCode errorCode) {
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