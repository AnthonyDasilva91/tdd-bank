package com.tddbank.kata.presentation.controller;

import com.tddbank.kata.domain.entity.AccountTransaction;
import com.tddbank.kata.presentation.request.TransferHistoryRequest;
import com.tddbank.kata.usecase.transaction.GetAccountOperationsUseCase;
import com.tddbank.kata.usecase.transaction.GetAllAccountTransactionsUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class HistoryTransactionController {

    private final GetAccountOperationsUseCase getAccountOperationsUseCase;
    private final GetAllAccountTransactionsUseCase getAllAccountTransactionsUseCase;

    public HistoryTransactionController(GetAccountOperationsUseCase getAccountOperationsUseCase,
                                        GetAllAccountTransactionsUseCase getAllAccountTransactionsUseCase) {
        this.getAccountOperationsUseCase = getAccountOperationsUseCase;
        this.getAllAccountTransactionsUseCase = getAllAccountTransactionsUseCase;
    }

    @GetMapping("/operations/{accountId}")
    public List<AccountTransaction> getAccountOperations(@PathVariable("accountId") UUID accountId) {
        return getAccountOperationsUseCase.getOperationsOf(accountId);
    }


    @GetMapping("/transfers")
    public List<AccountTransaction> getAccountsTransfers(@RequestBody TransferHistoryRequest transferHistoryRequest) {
        return getAllAccountTransactionsUseCase.getAllTransactionsOf(transferHistoryRequest.getAccountId(),
                transferHistoryRequest.getOtherAccountId());
    }
}
