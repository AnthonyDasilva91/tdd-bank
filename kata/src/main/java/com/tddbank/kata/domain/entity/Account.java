package com.tddbank.kata.domain.entity;

import com.tddbank.kata.domain.exception.NotEnoughMoneyException;
import com.tddbank.kata.domain.exception.NotValidAmountException;
import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Account {

    @Id
    @NonNull
    private final UUID id;

    @NonNull
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id.equals(account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
