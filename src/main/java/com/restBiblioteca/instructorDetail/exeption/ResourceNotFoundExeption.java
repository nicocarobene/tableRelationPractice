package com.restBiblioteca.instructorDetail.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class ResourceNotFoundExeption extends RuntimeException{
    public ResourceNotFoundExeption() {
    }
    public ResourceNotFoundExeption(String message) {
        super(message);
    }

    public ResourceNotFoundExeption(String message, Throwable error) {
        super(message, error);
    }
}
