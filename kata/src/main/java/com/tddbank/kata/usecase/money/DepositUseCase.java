package com.tddbank.kata.usecase.money;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.persistence.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class DepositUseCase {

    private final AccountRepository accountRepository;

    public DepositUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
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
    }
}
