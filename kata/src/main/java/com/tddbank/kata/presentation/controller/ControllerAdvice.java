package com.tddbank.kata.presentation.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler
    public ErrorResponse exceptionHandler(Exception exception) {
        return new ErrorResponse(exception.getMessage());
    }
}
