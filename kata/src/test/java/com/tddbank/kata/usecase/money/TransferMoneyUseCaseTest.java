package com.tddbank.kata.usecase.money;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.usecase.account.CreateAccountUseCase;
import com.tddbank.kata.usecase.port.AccountRepository;
import com.tddbank.kata.usecase.port.AccountRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        TransferMoneyUseCase transferMoneyUseCase = new TransferMoneyUseCase(accountRepository);

        Account payer = new Account();
        Account payee = new Account();

        // Add money on both accounts
        payer.deposit(100);
        payee.deposit(100);

        accountRepository.save(payer);
        accountRepository.save(payee);

        double expectedPayerAmount = 90;
        double expectedPayeeAmount = 110;

        // Act
        transferMoneyUseCase.transfer(payer.getId(), payee.getId(), 10);

        // Assert
        Optional<Account> optionalPayerAccount = accountRepository.findById(payer.getId());
        Optional<Account> optionalPayeeAccount = accountRepository.findById(payee.getId());

        assertTrue(optionalPayerAccount.isPresent());
        assertEquals(expectedPayerAmount, optionalPayerAccount.get().getAmount());

        assertTrue(optionalPayeeAccount.isPresent());
        assertEquals(expectedPayeeAmount, optionalPayeeAccount.get().getAmount());
    }
}
