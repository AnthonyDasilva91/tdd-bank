package com.tddbank.kata.usecase.transaction;

import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.persistence.AccountTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class GetAllAccountTransactionsUseCase {

    private final AccountTransactionRepository accountTransactionRepository;

    public GetAllAccountTransactionsUseCase(AccountTransactionRepository accountTransactionRepository) {
        this.accountTransactionRepository = accountTransactionRepository;
    }

    /**
     * Return all transfers made by both accounts
     * @param fromAccountId the id of the payer account
     * @param toAccountId the id of the payee account
     * @return a list containing the transfers made by both accounts
     */
    public List<AccountTransaction> getAllTransactionsOf(UUID fromAccountId, UUID toAccountId) {
        List<AccountTransaction> transactionOfAccountA =
                accountTransactionRepository.findTransactionsFromBothAccounts(fromAccountId, toAccountId);

        List<AccountTransaction> transactionOfAccountB =
                accountTransactionRepository.findTransactionsFromBothAccounts(toAccountId, fromAccountId);

        return Stream.concat(transactionOfAccountA.stream(), transactionOfAccountB.stream())
                .collect(Collectors.toList());
    }
}
