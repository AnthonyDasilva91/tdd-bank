import exception.AccountNotFoundException;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GetAccountUseCaseTest {

    @Test
    void should_throw_exception_when_not_existing_account() {
        // Arrange
        UUID notExistingAccountId = UUID.randomUUID();
        GetAccountUseCase getAccountUseCase = new GetAccountUseCase();

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> getAccountUseCase.get(notExistingAccountId));
    }

    @Test
    void should_return_account_when_account_exists() {
        // Arrange
        GetAccountUseCase getAccountUseCase = new GetAccountUseCase();
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCase();
        Account createdAccount = createAccountUseCase.create();

        // Act
        Account existingAccount = getAccountUseCase.get(createdAccount.getId());

        // Assert
        assertEquals(createdAccount, existingAccount);
    }
}
