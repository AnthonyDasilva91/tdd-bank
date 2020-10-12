package com.tddbank.usecase.account;

import com.tddbank.domain.entity.Account;
import com.tddbank.usecase.port.AccountRepository;
import com.tddbank.usecase.port.AccountRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateAccountUseCaseTest {

    private static CreateAccountUseCase createAccountUseCase;

    @BeforeAll
    static void setUp() {
        AccountRepository accountRepository = new AccountRepositoryImpl();
        createAccountUseCase = new CreateAccountUseCase(accountRepository);
    }

    @Test
    void createAccount() {
        // Arrange
        double expectedAmount = 0;

        // Act
        Account account = createAccountUseCase.create();

        // Assert
        Assertions.assertNotNull(account);
        Assertions.assertNotNull(account.getId());
        Assertions.assertEquals(expectedAmount, account.getAmount());
    }
}
