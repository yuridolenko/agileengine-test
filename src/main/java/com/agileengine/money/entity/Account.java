package com.agileengine.money.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by ded on 01.06.2017.
 */
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private BigDecimal moneyAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(BigDecimal moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void addMoney(BigDecimal amount) {
        this.moneyAmount = this.moneyAmount.add(amount);
    }

}
