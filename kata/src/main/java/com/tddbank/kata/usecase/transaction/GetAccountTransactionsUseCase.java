package com.tddbank.kata.usecase.transaction;

import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.persistence.AccountTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetAccountTransactionsUseCase {

    private final AccountTransactionRepository accountTransactionRepository;

    public GetAccountTransactionsUseCase(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    public List<AccountTransaction> getTransactionsOf(UUID accountId) {
        return accountTransactionRepository.findByFromAccountId(accountId);
    }
}
