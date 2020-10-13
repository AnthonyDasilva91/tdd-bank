package com.tddbank.kata.presentation.controller;

import com.tddbank.kata.domain.entity.Account;
import com.tddbank.kata.usecase.account.CreateAccountUseCase;
import com.tddbank.kata.usecase.account.GetAccountUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final GetAccountUseCase getAccountUseCase;
    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(GetAccountUseCase getAccountUseCase, CreateAccountUseCase createAccountUseCase) {
        this.getAccountUseCase = getAccountUseCase;
        this.createAccountUseCase = createAccountUseCase;
    }

    @GetMapping("/{accountId}")
    public Account getAccount(@PathVariable UUID accountId) {
        return getAccountUseCase.get(accountId);
    }

    @PostMapping
    public Account createAccount() {
        return createAccountUseCase.create();
    }
}
