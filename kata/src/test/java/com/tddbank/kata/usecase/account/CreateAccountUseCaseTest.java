package com.tddbank.kata.usecase.account;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.persistence.AccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CreateAccountUseCaseTest {

    @Autowired
    private AccountRepository accountRepository;

    private CreateAccountUseCase createAccountUseCase;

    @BeforeEach
    void setUp() {
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
