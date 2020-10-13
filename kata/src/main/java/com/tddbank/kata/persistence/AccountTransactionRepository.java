package com.tddbank.kata.persistence;

import com.tddbank.kata.domain.entity.AccountTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountTransactionRepository extends CrudRepository<AccountTransaction, UUID> {

    List<AccountTransaction> findByFromAccountId(UUID fromAccountId);
}
