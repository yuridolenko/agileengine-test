package com.agileengine.money.vo;

import com.agileengine.money.entity.Account;

/**
 * Created by ded on 01.06.2017.
 */
public class AccountVO implements IBaseVO {

    private Long id;

    private String name;

    public AccountVO(Account account) {
        id = account.getId();
        name = account.getName();
    }

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


}
