package com.tddbank.kata.usecase.money;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.domain.exception.NotValidAmountException;
import com.tddbank.kata.usecase.port.AccountRepository;
import com.tddbank.kata.usecase.port.AccountRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DepositUseCaseTest {

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
        AccountRepository accountRepository = new AccountRepositoryImpl();
        DepositUseCase depositUseCase = new DepositUseCase(accountRepository);

        double expected = 10;
        Account existingAccount = new Account();
        accountRepository.save(existingAccount);

        // Act
        depositUseCase.deposit(existingAccount.getId(), 10);

        // Assert
        Optional<Account> optionalAccount = accountRepository.findById(existingAccount.getId());
        assertTrue(optionalAccount.isPresent());
        assertEquals(expected, optionalAccount.get().getAmount());
    }
}
