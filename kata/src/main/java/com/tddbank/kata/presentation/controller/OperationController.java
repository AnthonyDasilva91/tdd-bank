package com.tddbank.kata.presentation.controller;

import com.tddbank.kata.presentation.request.OperationRequest;
import com.tddbank.kata.usecase.money.DepositUseCase;
import com.tddbank.kata.usecase.money.WithdrawUseCase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operations/")
public class OperationController {

    private final DepositUseCase depositUseCase;
    private final WithdrawUseCase withdrawUseCase;

    public OperationController(DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase) {
        this.depositUseCase = depositUseCase;
        this.withdrawUseCase = withdrawUseCase;
    }

    @PostMapping("/deposit")
    public void makeDeposit(@RequestBody OperationRequest operationRequest) {
        depositUseCase.deposit(operationRequest.getAccountId(), operationRequest.getAmount());
    }

    @PostMapping("/withdrawal")
    public void makeWithdrawal(@RequestBody OperationRequest operationRequest) {
        withdrawUseCase.withdraw(operationRequest.getAccountId(), operationRequest.getAmount());
    }
}
