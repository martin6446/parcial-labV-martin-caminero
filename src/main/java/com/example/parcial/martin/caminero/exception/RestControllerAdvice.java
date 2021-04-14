package com.example.parcial.martin.caminero.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler({PersonNotFoundException.class})
    public ResponseEntity<Object> personNotFoundHandler(PersonNotFoundException ex){
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex.getMessage(),errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(),apiError.getHttpStatus());
    }

    @ExceptionHandler({CurrencyNotFoundException.class})
    public ResponseEntity<Object> currencyNotFoundHandler(CurrencyNotFoundException ex){
        List<String> errors = new ArrayList<>();
        errors.add(ex.getLocalizedMessage());
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND,ex.getMessage(),errors);
        return new ResponseEntity<>(apiError, new HttpHeaders(),apiError.getHttpStatus());
    }
}
