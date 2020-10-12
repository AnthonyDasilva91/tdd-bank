package com.tddbank.usecase;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.AccountNotFoundException;
import com.tddbank.domain.exception.NotEnoughMoneyException;
import com.tddbank.domain.exception.NotValidAmountException;
import com.tddbank.usecase.account.CreateAccountUseCase;
import com.tddbank.usecase.account.GetAccountUseCase;
import com.tddbank.usecase.money.DepositUseCase;
import com.tddbank.usecase.money.WithdrawUseCase;
import com.tddbank.usecase.port.AccountRepository;
import com.tddbank.usecase.port.AccountRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
        assertThrows(NotValidAmountException.class, () -> withdrawUseCase.withdraw(existingAccountId, 0));
    }

    @Test
    void should_throw_exception_when_withdraw_is_negative() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(mockAccountRepository);

        // Act & Assert
        assertThrows(NotValidAmountException.class, () -> withdrawUseCase.withdraw(existingAccountId, -1));
    }

    @Test
    void should_throw_exception_when_account_does_not_exists() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.empty());

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(mockAccountRepository);

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> withdrawUseCase.withdraw(existingAccountId, -1));
    }

    @Test
    void should_throw_exception_when_not_enough_money_on_account() {

        // Arrange
        AccountRepository accountRepository = new AccountRepositoryImpl();

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(accountRepository);
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository);

        Account existingAccount = createAccountUseCase.create();

        // Act & Assert
        assertThrows(NotEnoughMoneyException.class, () -> withdrawUseCase.withdraw(existingAccount.getId(), 10));
    }

    @Test
    void amount_should_decrease_of_10_when_withdrawal_of_10() {

        // Arrange
        AccountRepository accountRepository = new AccountRepositoryImpl();

        DepositUseCase depositUseCase = new DepositUseCase(accountRepository);
        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(accountRepository);
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository);
        GetAccountUseCase getAccountUseCase = new GetAccountUseCase(accountRepository);

        double expected = 90;
        Account existingAccount = createAccountUseCase.create();

        // Act
        depositUseCase.deposit(existingAccount.getId(), 100);
        withdrawUseCase.withdraw(existingAccount.getId(), 10);

        // Assert
        Account accountWithDeposit = getAccountUseCase.get(existingAccount.getId());
        assertEquals(expected, accountWithDeposit.getAmount());
    }
}
