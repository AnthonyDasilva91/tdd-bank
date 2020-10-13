package com.tddbank.kata.usecase.money;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.domain.exception.AccountNotFoundException;
import com.tddbank.kata.persistence.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WithdrawUseCase {

    private final AccountRepository accountRepository;

    public WithdrawUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * Withdraw an amount of money from an account
     *
     * @param accountId the id of the account we want to make an withdrawal on
     * @param amount    the amount of the withdrawal
     * @throws AccountNotFoundException if no account found with this id
     */
    @Transactional
    public void withdraw(UUID accountId, double amount) {

        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found !"));

        account.withdraw(amount);

        accountRepository.save(account);
    }
}
