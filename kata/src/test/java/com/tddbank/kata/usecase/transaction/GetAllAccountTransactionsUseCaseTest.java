package com.tddbank.kata.usecase.transaction;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.persistence.AccountRepository;
import com.tddbank.kata.usecase.money.TransferMoneyUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetAllAccountTransactionsUseCaseTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransferMoneyUseCase transferMoneyUseCase;

    @Autowired
    private GetAllAccountTransactionsUseCase getAllAccountTransactionsUseCase;

    @Autowired
    void should_return_no_transaction_when_nothing_is_done() {

        // Arrange
        int expectedTransactionNumber = 0;

        // Act
        List<AccountTransaction> transactions = getAllAccountTransactionsUseCase.getAllTransactionsOf(UUID.randomUUID(), UUID.randomUUID());

        // Assert
        assertEquals(expectedTransactionNumber, transactions.size());
    }

    @Test
    void should_return_no_transaction_multiple_operations_done() {
        // Arrange
        Account accountA = new Account();
        accountA.deposit(100);
        accountRepository.save(accountA);

        Account accountB = new Account();
        accountB.deposit(100);
        accountRepository.save(accountB);

        Account accountC = new Account();
        accountC.deposit(100);
        accountRepository.save(accountC);

        transferMoneyUseCase.transfer(accountA.getId(), accountB.getId(), 50);
        transferMoneyUseCase.transfer(accountB.getId(), accountA.getId(), 20);

        transferMoneyUseCase.transfer(accountA.getId(), accountC.getId(), 20);
        transferMoneyUseCase.transfer(accountC.getId(), accountA.getId(), 20);

        int expectedTransactionNumber = 2;

        // Act
        List<AccountTransaction> transactions =
                getAllAccountTransactionsUseCase.getAllTransactionsOf(accountA.getId(), accountB.getId());

        // Assert
        assertEquals(expectedTransactionNumber, transactions.size());
    }
}
