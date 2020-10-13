package com.tddbank.kata.usecase.money;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.domain.exception.NotEnoughMoneyException;
import com.tddbank.kata.domain.exception.NotValidAmountException;
import com.tddbank.kata.persistence.AccountRepository;
import com.tddbank.kata.persistence.AccountTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class WithdrawUseCaseTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountTransactionRepository accountTransactionRepository;

    private AccountRepository mockAccountRepository;

    @BeforeEach
    void mock() {
        mockAccountRepository = Mockito.mock(AccountRepository.class);
    }

    @Test
    void should_throw_exception_when_withdraw_is_zero() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(mockAccountRepository, accountTransactionRepository);

        // Act & Assert
        assertThrows(NotValidAmountException.class, () -> withdrawUseCase.withdraw(existingAccountId, 0));
    }

    @Test
    void should_throw_exception_when_withdraw_is_negative() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.of(new Account()));

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(mockAccountRepository, accountTransactionRepository);

        // Act & Assert
        assertThrows(NotValidAmountException.class, () -> withdrawUseCase.withdraw(existingAccountId, -1));
    }

    @Test
    void should_throw_exception_when_account_does_not_exists() {

        // Arrange
        UUID existingAccountId = UUID.randomUUID();
        Mockito.when(mockAccountRepository.findById(existingAccountId)).thenReturn(Optional.empty());

        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(mockAccountRepository, accountTransactionRepository);

        // Act & Assert
        assertThrows(AccountNotFoundException.class, () -> withdrawUseCase.withdraw(existingAccountId, -1));
    }

    @Test
    void should_throw_exception_when_not_enough_money_on_account() {

        // Arrange
        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(accountRepository, accountTransactionRepository);

        Account existingAccount = new Account();
        accountRepository.save(existingAccount);

        // Act & Assert
        assertThrows(NotEnoughMoneyException.class, () -> withdrawUseCase.withdraw(existingAccount.getId(), 10));
    }

    @Test
    void amount_should_decrease_of_10_when_withdrawal_of_10() {

        // Arrange
        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(accountRepository, accountTransactionRepository);

        Account existingAccount = new Account();
        existingAccount.deposit(100);
        accountRepository.save(existingAccount);

        double expectedAmount = 90;
        double withdrawal = 10;

        // Act
        withdrawUseCase.withdraw(existingAccount.getId(), withdrawal);

        // Assert
        Optional<Account> optionalAccount = accountRepository.findById(existingAccount.getId());
        assertTrue(optionalAccount.isPresent());
        assertEquals(expectedAmount, optionalAccount.get().getAmount());
    }

    @Test
    void transaction_should_be_saved_when_a_withdrawal_is_made() {

        // Arrange
        WithdrawUseCase withdrawUseCase = new WithdrawUseCase(accountRepository, accountTransactionRepository);

        Account existingAccount = new Account();
        existingAccount.deposit(100);
        accountRepository.save(existingAccount);

        double withdrawal = 10;
        int expectedTransactionNumber = 1;

        // Act
        withdrawUseCase.withdraw(existingAccount.getId(), withdrawal);

        // Assert
        List<AccountTransaction> transactions = accountTransactionRepository.findByFromAccountId(existingAccount.getId());
        assertEquals(expectedTransactionNumber, transactions.size());

        AccountTransaction accountTransaction = transactions.get(0);
        assertEquals(existingAccount.getId(), accountTransaction.getFromAccountId());
        assertEquals(withdrawal, accountTransaction.getAmount());
    }
}
