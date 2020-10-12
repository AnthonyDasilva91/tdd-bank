package com.tddbank.domain.entity;

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
}
