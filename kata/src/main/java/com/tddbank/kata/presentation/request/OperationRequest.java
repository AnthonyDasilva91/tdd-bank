package com.tddbank.kata.presentation.request;

import java.util.UUID;

public class OperationRequest {

    private UUID accountId;

    private double amount;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
