package com.example.parcial.martin.caminero.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class PersonNotFoundException extends HttpClientErrorException {
    public PersonNotFoundException() {
        super(HttpStatus.NOT_FOUND, "the person was not found in our database");
    }
}
