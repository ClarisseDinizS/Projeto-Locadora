package com.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.backend.exception.RegistroNotFoundException;
import com.backend.exception.RelationFoundException;

@RestControllerAdvice
public class AplicationControllerAdvice {

    @ExceptionHandler(RegistroNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(RegistroNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(RelationFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleRelationFoundException(RelationFoundException ex) {
        return ex.getMessage();
    }
}
