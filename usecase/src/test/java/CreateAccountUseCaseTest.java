import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CreateAccountUseCaseTest {

    private static CreateAccountUseCase createAccountUseCase;

    @BeforeAll
    static void setUp() {
        AccountRepository accountRepository = new AccountRepository();
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
