import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class GetAccountUseCaseTest {

    @Test
    void should_throw_exception_when_not_existing_account() {
        // Arrange
        UUID notExistingAccountId = UUID.randomUUID();
        GetAccountUseCase getAccountUseCase = new GetAccountUseCase();

        // Act & Assert
        Assertions.assertThrows(AccountNotFoundException.class, getAccountUseCase.get(notExistingAccountId));
    }
}
