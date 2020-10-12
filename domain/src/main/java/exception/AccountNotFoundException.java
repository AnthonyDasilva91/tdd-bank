package exception;

public class AccountNotFoundException extends DomainException {

    public AccountNotFoundException(String message) {
        super(message);
    }
}
