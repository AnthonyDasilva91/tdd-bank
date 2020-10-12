package com.tddbank.kata.usecase.account;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.usecase.port.AccountRepository;

public class CreateAccountUseCase {

    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Create a new account
     * @return the newly created account
     */
    public Account create() {
        Account newAccount = new Account();
        accountRepository.save(newAccount);
        return newAccount;
    }
}
