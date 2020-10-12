package com.tddbank.usecase.money;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.AccountNotFoundException;
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

        account.deposit(amount);

        accountRepository.save(account);
    }
}
