package com.tddbank.kata.domain.entity;

import org.springframework.lang.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class AccountTransaction {

    @Id
    @NonNull
    private final UUID id;

    @NonNull
    private Double amount;

    @NonNull
    private UUID fromAccountId;

    private UUID toAccountId;

    public AccountTransaction() {
        this.id = UUID.randomUUID();
    }

    public AccountTransaction(UUID fromAccountId, double amount) {
        this();

        this.fromAccountId = fromAccountId;
        this.amount = amount;
    }

    public AccountTransaction(UUID fromAccountId, UUID toAccountId, double amount) {
        this();

        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }

    @NonNull
    public UUID getId() {
        return id;
    }

    @NonNull
    public Double getAmount() {
        return amount;
    }

    @NonNull
    public UUID getFromAccountId() {
        return fromAccountId;
    }

    public UUID getToAccountId() {
        return toAccountId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountTransaction that = (AccountTransaction) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
