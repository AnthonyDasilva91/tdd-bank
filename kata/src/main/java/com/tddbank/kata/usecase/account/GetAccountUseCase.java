package com.tddbank.kata.usecase.account;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.persistence.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
public class GetAccountUseCase {

    private final AccountRepository accountRepository;

    public GetAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * @param accountId the id of the account we want to retrieve
     * @return corresponding account
     * @throws NullPointerException     when accountId is null
     * @throws AccountNotFoundException if no account found with this id
     */
    @Transactional(readOnly = true)
    public Account get(UUID accountId) {
        Objects.requireNonNull(accountId);

        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("No account found with this id"));
    }
}
