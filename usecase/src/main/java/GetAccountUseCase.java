import java.util.UUID;

public class GetAccountUseCase {

    public Object get(UUID notExistingAccountId) {
        throw new AccountNotFoundException("No account found with this id");
    }
}
