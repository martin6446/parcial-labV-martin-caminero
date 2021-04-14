package com.example.parcial.martin.caminero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class CurrencyNotFoundException extends HttpClientErrorException {
    public CurrencyNotFoundException() {
        super(HttpStatus.NOT_FOUND, "currency not found in our database");
    }
}
