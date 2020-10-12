package com.tddbank.domain.exception;

public class NotEnoughMoneyException extends DomainException {

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
