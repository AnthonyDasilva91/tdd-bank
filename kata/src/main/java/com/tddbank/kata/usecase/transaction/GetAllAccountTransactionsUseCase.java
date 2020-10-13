package com.tddbank.kata.usecase.transaction;

import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.persistence.AccountTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetAllAccountTransactionsUseCase {

    private final AccountTransactionRepository accountTransactionRepository;

    public GetAllAccountTransactionsUseCase(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    public List<AccountTransaction> getAllTransactionsOf(UUID accountId) {
        return new ArrayList<>();
    }
}
