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

    public List<AccountTransaction> getAllTransactionsOf(UUID accountA, UUID accountB) {
        List<AccountTransaction> transactionOfAccountA =
                accountTransactionRepository.findTransactionsFromBothAccounts(accountA, accountB);

        List<AccountTransaction> transactionOfAccountB =
                accountTransactionRepository.findTransactionsFromBothAccounts(accountB, accountA);

        return Stream.concat(transactionOfAccountA.stream(), transactionOfAccountB.stream())
                .collect(Collectors.toList());
    }
}
