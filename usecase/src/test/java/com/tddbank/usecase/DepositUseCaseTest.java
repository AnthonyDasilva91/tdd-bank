package com.tddbank.usecase;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.NotValidAmountException;
import com.tddbank.usecase.port.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class DepositUseCaseTest {

    private AccountRepository accountRepository;

    @BeforeEach
    void mock() {
        accountRepository = Mockito.mock(AccountRepository.class);
    }

    @Test
    void should_throw_exception_when_deposit_is_zero() {
        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(accountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));
        DepositUseCase depositUseCase = new DepositUseCase(accountRepository);

        // Act & Assert
        Assertions.assertThrows(NotValidAmountException.class, () -> depositUseCase.deposit(existingAccountId, 0));
    }
}
