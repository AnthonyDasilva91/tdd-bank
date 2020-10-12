package com.tddbank.usecase;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.AccountNotFoundException;
import com.tddbank.domain.exception.NotValidAmountException;
import com.tddbank.usecase.account.CreateAccountUseCase;
import com.tddbank.usecase.account.GetAccountUseCase;
import com.tddbank.usecase.port.AccountRepository;
import com.tddbank.usecase.port.AccountRepositoryImpl;
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
        Assertions.assertThrows(NotValidAmountException.class, () -> depositUseCase.deposit(existingAccountId, 0));
    }

    @Test
    void should_throw_exception_when_deposit_is_negative() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        DepositUseCase depositUseCase = new DepositUseCase(mockAccountRepository);

        // Act & Assert
        Assertions.assertThrows(NotValidAmountException.class, () -> depositUseCase.deposit(existingAccountId, -1));
    }

    @Test
    void should_throw_exception_when_account_does_not_exists() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.empty());

        DepositUseCase depositUseCase = new DepositUseCase(mockAccountRepository);

        // Act & Assert
        Assertions.assertThrows(AccountNotFoundException.class, () -> depositUseCase.deposit(existingAccountId, -1));
    }

    @Test
    void amount_should_be_10_when_deposit_is_10() {

        // Arrange
        AccountRepository accountRepository = new AccountRepositoryImpl();

        DepositUseCase depositUseCase = new DepositUseCase(accountRepository);
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository);
        GetAccountUseCase getAccountUseCase = new GetAccountUseCase(accountRepository);

        double expected = 10;
        Account existingAccount = createAccountUseCase.create();

        // Act
        depositUseCase.deposit(existingAccount.getId(), 10);

        // Assert
        Account accountWithDeposit = getAccountUseCase.get(existingAccount.getId());
        Assertions.assertEquals(expected, accountWithDeposit.getAmount());
    }
}
