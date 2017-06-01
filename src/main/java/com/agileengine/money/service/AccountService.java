package com.agileengine.money.service;

import com.agileengine.money.entity.Account;
import com.agileengine.money.repository.AccountRepository;
import com.agileengine.money.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ded on 01.06.2017.
 */
@Service
public class AccountService implements IAccountService{

    @Autowired
    private AccountRepository accountRepository;

    public List<AccountVO> read() {
        List<AccountVO> result = new ArrayList<>();
        Iterable<Account> accounts = accountRepository.findAll();
        accounts.forEach(account -> result.add(new AccountVO(account)));
        return result;
    }
}
