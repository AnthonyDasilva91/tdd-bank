package com.tddbank.usecase;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.AccountNotFoundException;
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
public class WithdrawUseCaseTest {

    private AccountRepository mockAccountRepository;

    @BeforeEach
    void mock() {
        mockAccountRepository = Mockito.mock(AccountRepository.class);
    }

    @Test
    void should_throw_exception_when_withdraw_is_zero() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(mockAccountRepository);

        // Act & Assert
        Assertions.assertThrows(NotValidAmountException.class, () -> withdrawUseCase.withdraw(existingAccountId, 0));
    }

    @Test
    void should_throw_exception_when_withdraw_is_negative() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(mockAccountRepository);

        // Act & Assert
        Assertions.assertThrows(NotValidAmountException.class, () -> withdrawUseCase.withdraw(existingAccountId, -1));
    }

    @Test
    void should_throw_exception_when_account_does_not_exists() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(mockAccountRepository);

        // Act & Assert
        Assertions.assertThrows(AccountNotFoundException.class, () -> withdrawUseCase.withdraw(existingAccountId, -1));
    }
}
