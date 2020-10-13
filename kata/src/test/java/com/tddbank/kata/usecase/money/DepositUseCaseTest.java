package com.tddbank.kata.usecase.money;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.domain.exception.NotValidAmountException;
import com.tddbank.kata.persistence.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class DepositUseCaseTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    private AccountRepository mockAccountRepository;

    @BeforeEach
    void mock() {
        mockAccountRepository = Mockito.mock(AccountRepository.class);
    }

    @Test
    void should_throw_exception_when_deposit_is_zero() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        DepositUseCase depositUseCase = new DepositUseCase(mockAccountRepository);

        // Act & Assert
        assertThrows(NotValidAmountException.class, () -> depositUseCase.deposit(existingAccountId, 0));
    }

    @Test
    void should_throw_exception_when_deposit_is_negative() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        DepositUseCase depositUseCase = new DepositUseCase(mockAccountRepository);

        // Act & Assert
        assertThrows(NotValidAmountException.class, () -> depositUseCase.deposit(existingAccountId, -1));
    }

    @Test
    void should_throw_exception_when_account_does_not_exists() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.empty());

        DepositUseCase depositUseCase = new DepositUseCase(mockAccountRepository);

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> depositUseCase.deposit(existingAccountId, -1));
    }

    @Test
    void amount_should_be_10_when_deposit_is_10() {

        // Arrange
        DepositUseCase depositUseCase = new DepositUseCase(accountRepository);

        Account existingAccount = new Account();
        accountRepository.save(existingAccount);

        double expectedAmount = 10;
        int expectedTransactionNumber = 1;

        // Act
        depositUseCase.deposit(existingAccount.getId(), 10);

        // Assert
        Optional<Account> optionalAccount = accountRepository.findById(existingAccount.getId());
        assertTrue(optionalAccount.isPresent());
        assertEquals(expectedAmount, optionalAccount.get().getAmount());

        List<AccountTransaction> transactions = accountTransactionRepository.findByAccountId(existingAccount.getId());
        assertEquals(expectedTransactionNumber, transactions.size());
        assertEquals(AccountTransactionType.DEPOSIT, expectedTransactionNumber.get(0).getType());
        assertEquals(expectedAmount, expectedTransactionNumber.get(0).getAmount());
    }
}
