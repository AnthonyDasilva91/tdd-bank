package com.tddbank.kata.usecase.account;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.persistence.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class GetAccountUseCaseTest {

    @Autowired
    private AccountRepository accountRepository;

    private CreateAccountUseCase createAccountUseCase;
    private GetAccountUseCase getAccountUseCase;

    @BeforeEach
    void setUp() {
        createAccountUseCase = new CreateAccountUseCase(accountRepository);
        getAccountUseCase = new GetAccountUseCase(accountRepository);
    }

    @Test
    void should_throw_exception_when_not_existing_account() {
        // Arrange
        UUID notExistingAccountId = UUID.randomUUID();

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> getAccountUseCase.get(notExistingAccountId));
    }

    @Test
    void should_return_account_when_account_exists() {
        // Arrange
        Account createdAccount = createAccountUseCase.create();

        // Act
        Account existingAccount = getAccountUseCase.get(createdAccount.getId());

        // Assert
        assertEquals(createdAccount, existingAccount);
    }

    @Test
    void should_throw_exception_when_account_id_is_null() {
        // Act & Assert
        assertThrows(NullPointerException.class, () -> getAccountUseCase.get(null));
    }
}
