package com.agileengine.money.vo;

import com.agileengine.money.entity.MoneyTransaction;
import com.agileengine.money.entity.TransactionType;

import javax.validation.constraints.NotNull;

/**
 * Created by ded on 01.06.2017.
 */
public class MoneyTransactionVO implements IBaseVO {

    private Long id;

    @NotNull
    private TransactionType type;

    private String accountName;

    @NotNull
    private Long accountId;

    private float amount;

    public MoneyTransactionVO() {
    }

    public MoneyTransactionVO(MoneyTransaction transaction) {
        id = transaction.getId();
        type = transaction.getType();
        accountName = transaction.getAccount().getName();
        accountId = transaction.getAccount().getId();
        amount = transaction.getAmount();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getSignedAmount() {
        return TransactionType.CREDIT.equals(type) ? -amount : amount;
    }
}
