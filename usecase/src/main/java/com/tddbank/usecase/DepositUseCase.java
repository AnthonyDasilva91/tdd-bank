package com.tddbank.usecase;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.AccountNotFoundException;
import com.tddbank.domain.exception.NotValidAmountException;
import com.tddbank.usecase.port.AccountRepository;

import java.util.UUID;

public class DepositUseCase {

    private final AccountRepository accountRepository;

    public DepositUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void deposit(UUID accountId, double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found !"));

        if (amount <= 0) {
            throw new NotValidAmountException("Amount cannot be zero !");
        }

        account.deposit(amount);
        accountRepository.save(account);
    }
}
