package com.sinanmutlu.vendingmachine.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(ProductException.class)
    public final ResponseEntity<ProductException> handleException(ProductException ex) {
        return prepareResponse(ex);
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Exception> handleException(Exception ex) {
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus()).body(ex);
    }

    private static ResponseEntity<ProductException> prepareResponse(ProductException exception) {
        return ResponseEntity.status(exception.getErrorCode().getHttpStatus()).body(exception);
    }
}
