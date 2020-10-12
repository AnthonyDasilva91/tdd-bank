package com.tddbank.usecase.port;

import com.tddbank.domain.entity.Account;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AccountRepositoryImpl implements AccountRepository {

    private final Map<UUID, Account> accounts = new HashMap<>();

    public Account findById(UUID accountId) {
        return accounts.get(accountId);
    }

    public void save(Account account) {
        accounts.put(account.getId(), account);
    }
}
