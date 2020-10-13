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
public class TransferMoneyUseCase {

    private final AccountRepository accountRepository;
    private final AccountTransactionRepository accountTransactionRepository;

    public TransferMoneyUseCase(AccountRepository accountRepository, AccountTransactionRepository accountTransactionRepository) {
        this.accountRepository = accountRepository;
        this.accountTransactionRepository = accountTransactionRepository;
    }

    /**
     * Transfer a certain amount of money from a payer account onto a payee account
     * @param payerAccountId the id of the payer
     * @param payeeAccountId the id of the payee
     * @param amount the amount of money to transfer
     * @throws AccountNotFoundException if the payer account or the payee account is not found
     */
    @Transactional
    public void transfer(UUID payerAccountId, UUID payeeAccountId, double amount) {

        Account payer = accountRepository.findById(payerAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Payer account not found !"));

        Account payee = accountRepository.findById(payeeAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Payee account not found !"));

        payer.withdraw(amount);
        payee.deposit(amount);

        accountRepository.save(payer);
        accountRepository.save(payee);

        accountTransactionRepository.save(new AccountTransaction(payer.getId(), payee.getId(), amount));
    }
}
