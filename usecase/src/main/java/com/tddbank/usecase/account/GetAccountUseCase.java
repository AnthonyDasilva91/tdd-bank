package com.tddbank.usecase.account;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.AccountNotFoundException;
import com.tddbank.usecase.port.AccountRepository;

import java.util.UUID;

public class GetAccountUseCase {

    private final AccountRepository accountRepository;

    public GetAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * @param accountId the id of the account we want to retrieve
     * @return corresponding account
     * @throws AccountNotFoundException if no account found with this id
     */
    public Account get(UUID accountId) {

        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("No account found with this id");
        }

        return account;
    }
}
