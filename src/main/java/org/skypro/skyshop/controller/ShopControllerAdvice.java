package org.skypro.skyshop.controller;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.exceptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopControllerAdvice {
    private static final String ERROR_CODE = "PRODUCT_NOT_FOUND";
    private static final String ERROR_MESSAGE = "Product not found";

    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException() {
        ShopError error = new ShopError(ERROR_CODE, ERROR_MESSAGE);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
