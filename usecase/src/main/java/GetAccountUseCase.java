import exception.AccountNotFoundException;

import java.util.UUID;

public class GetAccountUseCase {

    private final AccountRepository accountRepository;

    public GetAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account get(UUID accountId) {

        Account account = accountRepository.findById(accountId);
        if (account == null) {
            throw new AccountNotFoundException("No account found with this id");
        }

        return account;
    }
}
