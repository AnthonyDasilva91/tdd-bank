package com.tddbank.kata.usecase.transaction;

import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.persistence.AccountTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class GetAccountOperationsUseCase {

    private final AccountTransactionRepository accountTransactionRepository;

    public GetAccountOperationsUseCase(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    /**
     * Return all the operations (deposit and withdrawal) of a specific account
     * @param accountId the id of the account we want to retrieve it's operations
     * @return a list containing the operations of the account
     */
    public List<AccountTransaction> getOperationsOf(UUID accountId) {
        return accountTransactionRepository.findOperationsOf(accountId);
    }
}
