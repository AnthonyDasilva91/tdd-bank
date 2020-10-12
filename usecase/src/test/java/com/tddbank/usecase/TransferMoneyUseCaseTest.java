package com.tddbank.usecase;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.AccountNotFoundException;
import com.tddbank.usecase.account.CreateAccountUseCase;
import com.tddbank.usecase.account.GetAccountUseCase;
import com.tddbank.usecase.money.DepositUseCase;
import com.tddbank.usecase.money.TransferMoneyUseCase;
import com.tddbank.usecase.port.AccountRepository;
import com.tddbank.usecase.port.AccountRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransferMoneyUseCaseTest {

    @Test
    void should_throw_exception_when_payer_not_exists() {
        // Arrange
        AccountRepository accountRepository = new AccountRepositoryImpl();
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository);
        TransferMoneyUseCase transferMoneyUseCase = new TransferMoneyUseCase(accountRepository);

        UUID notExistingPayerId = UUID.randomUUID();
        UUID payeeAccountId = createAccountUseCase.create().getId();

        // Act & Assert
        assertThrows(AccountNotFoundException.class,
                () -> transferMoneyUseCase.transfer(notExistingPayerId, payeeAccountId, 10));
    }

    @Test
    void should_throw_exception_when_payee_not_exists() {
        // Arrange
        AccountRepository accountRepository = new AccountRepositoryImpl();
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository);
        TransferMoneyUseCase transferMoneyUseCase = new TransferMoneyUseCase(accountRepository);

        UUID notExistingPayeeId = UUID.randomUUID();
        UUID payerAccountId = createAccountUseCase.create().getId();

        // Act & Assert
        assertThrows(AccountNotFoundException.class,
                () -> transferMoneyUseCase.transfer(payerAccountId, notExistingPayeeId, 10));
    }

    @Test
    void should_throw_exception_when_payee_not_exist() {

        // Arrange
        AccountRepository accountRepository = new AccountRepositoryImpl();
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository);
        DepositUseCase depositUseCase = new DepositUseCase(accountRepository);
        GetAccountUseCase getAccountUseCase = new GetAccountUseCase(accountRepository);
        TransferMoneyUseCase transferMoneyUseCase = new TransferMoneyUseCase(accountRepository);

        UUID payerAccountId = createAccountUseCase.create().getId();
        UUID payeeAccountId = createAccountUseCase.create().getId();

        // Add money on both accounts
        depositUseCase.deposit(payerAccountId, 100);
        depositUseCase.deposit(payeeAccountId, 100);

        double expectedPayerAmount = 90;
        double expectedPayeeAmount = 110;

        // Act
        transferMoneyUseCase.transfer(payerAccountId, payeeAccountId, 10);

        // Assert
        Account payer = getAccountUseCase.get(payerAccountId);
        Account payee = getAccountUseCase.get(payeeAccountId);

        assertEquals(expectedPayerAmount, payer.getAmount());
        assertEquals(expectedPayeeAmount, payee.getAmount());
    }
}
