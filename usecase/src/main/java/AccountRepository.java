import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountRepository {

    private Map<UUID, Account> accounts = new HashMap<>();

    public Account findById(UUID accountId) {
        return accounts.get(accountId);
    }

    public void save(Account account) {
        accounts.put(account.getId(), account);
    }
}