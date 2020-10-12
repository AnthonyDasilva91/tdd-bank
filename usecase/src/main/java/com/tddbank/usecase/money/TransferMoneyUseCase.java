package com.tddbank.usecase.money;

import com.tddbank.domain.entity.Account;
import com.tddbank.domain.exception.AccountNotFoundException;
import com.tddbank.usecase.port.AccountRepository;

import java.util.UUID;

public class TransferMoneyUseCase {

    private final AccountRepository accountRepository;

    public TransferMoneyUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void transfer(UUID payerAccountId, UUID payeeAccountId, double amount) {

        Account payer = accountRepository.findById(payerAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Payer account not found !"));

        Account payee = accountRepository.findById(payeeAccountId)
                .orElseThrow(() -> new AccountNotFoundException("Payee account not found !"));

        payer.withdraw(amount);
        payee.deposit(amount);

        accountRepository.save(payer);
        accountRepository.save(payee);
    }
}
