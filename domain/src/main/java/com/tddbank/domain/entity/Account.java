package com.tddbank.domain.entity;

import com.tddbank.domain.exception.NotEnoughMoneyException;
import com.tddbank.domain.exception.NotValidAmountException;

import java.util.UUID;

public class Account {

    private UUID id;
    private double amount;

    public Account() {
        this.id = UUID.randomUUID();
        this.amount = 0;
    }

    public UUID getId() {
        return this.id;
    }

    public double getAmount() {
        return this.amount;
    }

    public void deposit(double amount) {

        if (amount <= 0) {
            throw new NotValidAmountException("Amount cannot be negative or zero !");
        }

        this.amount += amount;
    }

    public void withdraw(double amount) {

        if (amount <= 0) {
            throw new NotValidAmountException("Amount cannot be negative or zero !");
        }

        if (amount > this.amount) {
            throw new NotEnoughMoneyException("Not enough money for the withdrawal");
        }

        this.amount -= amount;
    }
}
