import exception.AccountNotFoundException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetAccountUseCaseTest {

    private static CreateAccountUseCase createAccountUseCase;
    private static GetAccountUseCase getAccountUseCase;

    @BeforeAll
    static void setUp() {
        AccountRepository accountRepository = new AccountRepository();
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
}
