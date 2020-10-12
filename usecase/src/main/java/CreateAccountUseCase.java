public class CreateAccountUseCase {

    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account create() {
        Account newAccount = new Account();
        accountRepository.save(newAccount);
        return newAccount;
    }
}
