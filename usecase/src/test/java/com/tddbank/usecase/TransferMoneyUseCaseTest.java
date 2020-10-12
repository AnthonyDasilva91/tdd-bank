package com.tddbank.usecase;

import com.tddbank.domain.exception.AccountNotFoundException;
import com.tddbank.usecase.account.CreateAccountUseCase;
import com.tddbank.usecase.port.AccountRepository;
import com.tddbank.usecase.port.AccountRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TransferMoneyUseCaseTest {

    @Test
    void should_throw_exception_when_payer_not_exists() {
        // Arrange
        AccountRepository accountRepository = new AccountRepositoryImpl();
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase(accountRepository);
        TransferMoneyUseCase transferMoneyUseCase = new TransferMoneyUseCase();

        UUID notExistingPayerId = UUID.randomUUID();
        UUID payeeAccountId = createAccountUseCase.create().getId();

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> transferMoneyUseCase.transfer(notExistingPayerId, payeeAccountId, 10));
    }
}
