package com.tddbank.kata.domain.exception;

public class NotEnoughMoneyException extends DomainException {

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
