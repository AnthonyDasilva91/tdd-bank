import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreateAccountUseCaseTest {

    @Test
    void createAccount() {
        // Arrange
        double expectedAmount = 0;
        CreateAccountUseCase createAccountUseCase = new CreateAccountUseCaseTest();

        // Act
        Acount account = createAccountUseCase.create();

        // Assert
        Assertions.assertNotNull(account.getId());
        Assertions.assertEquals(expectedAmount, account.getAmount());
    }
}
