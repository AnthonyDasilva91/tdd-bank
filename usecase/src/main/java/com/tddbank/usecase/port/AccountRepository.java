package com.tddbank.usecase.port;

import com.tddbank.domain.entity.Account;

import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {

    Optional<Account> findById(UUID accountId);

    void save(Account account);
}

