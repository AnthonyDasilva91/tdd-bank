package com.tddbank.kata.presentation.request;

import java.util.UUID;

public class TransferHistoryRequest {

    private UUID accountId;

    private UUID otherAccountId;

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public UUID getOtherAccountId() {
        return otherAccountId;
    }

    public void setOtherAccountId(UUID otherAccountId) {
        this.otherAccountId = otherAccountId;
    }
}
