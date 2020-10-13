package com.tddbank.kata.usecase.transaction;

import com.tddbank.kata.domain.entity.AccountTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GetAllAccountTransactionsUseCaseTest {

    @Autowired
    private GetAllAccountTransactionsUseCase getAllAccountTransactionsUseCase;

    @Autowired
    void should_return_no_transaction_when_nothing_is_done() {

        // Arrange
        int expectedTransactionNumber = 0;

        // Act
        List<AccountTransaction> transactions = getAllAccountTransactionsUseCase.getAllTransactionsOf(UUID.randomUUID());

        // Assert
        assertEquals(expectedTransactionNumber, transactions.size());
    }
}
