package com.agileengine.money.entity;

import javax.persistence.*;

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

    private float moneyAmount;

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

    public Float getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(Float moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    public void addMoney(float amount) {
        this.moneyAmount += amount;
    }

}
