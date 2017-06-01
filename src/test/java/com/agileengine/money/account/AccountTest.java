package com.agileengine.money.account;

import com.agileengine.money.BaseTest;
import com.agileengine.money.entity.Account;
import com.agileengine.money.repository.AccountRepository;
import com.agileengine.money.repository.MoneyTransactionRepository;
import com.agileengine.money.service.IAccountService;
import com.agileengine.money.vo.AccountVO;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ded on 01.06.2017.
 */
public class AccountTest extends BaseTest{

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @Autowired
    IAccountService accountService;

    @Before
    public void setUp() throws Exception {
        moneyTransactionRepository.deleteAll();
        accountRepository.deleteAll();
    }

    @Test
    public void createAndGetTest() {
        for (int i = 0; i < 10; i++) {
            Account account = new Account();
            account.setName(uuid());
            account.setMoneyAmount(100f);
            accountRepository.save(account);
        }

        List<AccountVO> accounts  = accountService.read();
        assertNotNull(accounts);
        assertEquals(10, accounts.size());

        accounts.forEach(account -> {
            assertNotNull(account.getName());
            assertNotNull(account.getId());
        });
    }

}
