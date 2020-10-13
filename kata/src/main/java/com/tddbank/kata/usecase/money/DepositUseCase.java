package com.tddbank.kata.usecase.money;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.persistence.AccountRepository;
import com.tddbank.kata.persistence.AccountTransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DepositUseCase {

    private final AccountRepository accountRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    public DepositUseCase(AccountRepository accountRepository, AccountTransactionRepository accountTransactionRepository) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    /**
     * Deposit an amount of money in an account
     * @param accountId the id of the account we want to make a deposit on
     * @param amount the amount of the deposit
     * @throws AccountNotFoundException if no account found with this id
     */
    @Transactional
    public void deposit(UUID accountId, double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found !"));

        account.deposit(amount);

        accountRepository.save(account);
        accountTransactionRepository.save(new AccountTransaction(accountId, amount));
    }
}
