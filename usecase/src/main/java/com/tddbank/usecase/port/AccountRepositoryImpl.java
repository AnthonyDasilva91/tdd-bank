package com.tddbank.usecase.port;

import com.tddbank.domain.entity.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class AccountRepositoryImpl implements AccountRepository {

    private final Map<UUID, Account> accounts = new HashMap<>();

    public Optional<Account> findById(UUID accountId) {
        if (accounts.containsKey(accountId)) {
            return Optional.of(accounts.get(accountId));
        }
        return Optional.empty();
    }

    public void save(Account account) {
        accounts.put(account.getId(), account);
    }
}
