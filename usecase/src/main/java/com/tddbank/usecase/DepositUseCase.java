package com.tddbank.usecase;

import com.tddbank.domain.exception.NotValidAmountException;
import com.tddbank.usecase.port.AccountRepository;

import java.util.UUID;

public class DepositUseCase {

    private final AccountRepository accountRepository;

    public DepositUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deposit(UUID accountId, double amount) {

        accountRepository.findById(accountId).ifPresent(account -> {
            if (amount <= 0) {
                throw new NotValidAmountException("Amount cannot be zero !");
            }
        });
    }
}
