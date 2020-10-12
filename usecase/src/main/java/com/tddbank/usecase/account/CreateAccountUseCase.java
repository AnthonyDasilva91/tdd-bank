package com.tddbank.usecase.account;

import com.tddbank.domain.entity.Account;
import com.tddbank.usecase.port.AccountRepository;

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
