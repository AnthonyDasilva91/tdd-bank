package com.tddbank.kata.presentation.controller;

import com.tddbank.kata.presentation.request.TransferRequest;
import com.tddbank.kata.usecase.money.TransferMoneyUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {

    private final TransferMoneyUseCase transferMoneyUseCase;

    public TransferController(TransferMoneyUseCase transferMoneyUseCase) {
        this.transferMoneyUseCase = transferMoneyUseCase;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransferRequest transferRequest) {
        transferMoneyUseCase.transfer(transferRequest.getFromAccountId(), transferRequest.getToAccountId(),
                transferRequest.getAmount());
    }
}
