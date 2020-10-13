package com.tddbank.kata.usecase.transaction;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.persistence.AccountRepository;
import com.tddbank.kata.usecase.money.DepositUseCase;
import com.tddbank.kata.usecase.money.WithdrawUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetAccountTransactionsUseCaseTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private DepositUseCase depositUseCase;
    @Autowired
    private WithdrawUseCase withdrawUseCase;
    @Autowired
    private GetAccountTransactionsUseCase getAccountTransactionsUseCase;

    @Test
    void should_return_no_transaction_when_nothing_is_done() {
        // Arrange
        int expectedTransactionNumber = 0;

        // Act
        List<AccountTransaction> transactions = getAccountTransactionsUseCase.getTransactionsOf(UUID.randomUUID());

        // Assert
        assertEquals(expectedTransactionNumber, transactions.size());
    }

    @Test
    void should_return_no_transaction_multiple_operations_done() {
        // Arrange
        Account account = new Account();
        accountRepository.save(account);

        depositUseCase.deposit(account.getId(), 100);
        depositUseCase.deposit(account.getId(), 200);
        withdrawUseCase.withdraw(account.getId(), 33);
        int expectedTransactionNumber = 3;

        // Act
        List<AccountTransaction> transactions = getAccountTransactionsUseCase.getTransactionsOf(account.getId());

        // Assert
        assertEquals(expectedTransactionNumber, transactions.size());
    }
}
