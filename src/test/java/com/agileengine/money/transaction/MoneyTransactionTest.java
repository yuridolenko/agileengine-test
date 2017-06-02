package com.agileengine.money.transaction;

import com.agileengine.money.BaseTest;
import com.agileengine.money.entity.Account;
import com.agileengine.money.entity.MoneyTransaction;
import com.agileengine.money.entity.TransactionType;
import com.agileengine.money.repository.AccountRepository;
import com.agileengine.money.repository.MoneyTransactionRepository;
import com.agileengine.money.service.IMoneyTransactionService;
import com.agileengine.money.service.exception.AccountNotFoundException;
import com.agileengine.money.service.exception.NotEnoughMoneyException;
import com.agileengine.money.vo.MoneyTransactionVO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ded on 01.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MoneyTransactionTest extends BaseTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    MoneyTransactionRepository moneyTransactionRepository;

    @Autowired
    IMoneyTransactionService moneyTransactionService;

    Long accountId;

    @Before
    public void setUp() throws Exception {
        // remove all transactions
        moneyTransactionRepository.deleteAll();

        // Create test account
        Account account = new Account();
        account.setName(uuid());
        account.setMoneyAmount(new BigDecimal(1000));
        accountRepository.save(account);
        accountId = account.getId();
    }

    @Test
    public void readTest() {
        Account account = accountRepository.findOne(accountId);
        for (int i = 0; i < 10; i++) {
            MoneyTransaction transaction = new MoneyTransaction(account, TransactionType.CREDIT, new BigDecimal(100f));
            moneyTransactionRepository.save(transaction);
        }

        List<MoneyTransactionVO> transactions = moneyTransactionService.read();
        assertNotNull(transactions);
        assertEquals(10, transactions.size());

        transactions.forEach(transaction -> {
            assertNotNull(transaction.getAccountId());
            assertNotNull(transaction.getType());
            assertNotNull(transaction.getId());
        });
    }

    @Test(expected = NotEnoughMoneyException.class)
    public void notEnoughMoneyTest() throws AccountNotFoundException, NotEnoughMoneyException {
        createTransaction(TransactionType.CREDIT, new BigDecimal(2000));
    }

    @Test
    public void transactionTest() throws AccountNotFoundException, NotEnoughMoneyException {
        createTransaction(TransactionType.CREDIT, new BigDecimal(500));

        Account account = accountRepository.findOne(accountId);
        assertEquals(500f, account.getMoneyAmount().floatValue(), 0);

        createTransaction(TransactionType.DEBIT, new BigDecimal(600));
        account = accountRepository.findOne(accountId);
        assertEquals(1100f, account.getMoneyAmount().floatValue(), 0);

        List<MoneyTransactionVO> transactions = moneyTransactionService.read();
        assertNotNull(transactions);
        assertEquals(2, transactions.size());
        assertTrue(transactions.stream()
                .anyMatch(vo -> TransactionType.CREDIT.equals(vo.getType()) && new BigDecimal(500).compareTo(vo.getAmount()) == 0));
        assertTrue(transactions.stream()
                .anyMatch(vo -> TransactionType.DEBIT.equals(vo.getType()) && new BigDecimal(600).compareTo(vo.getAmount()) == 0));
    }

    private void createTransaction(TransactionType type, BigDecimal amount) throws AccountNotFoundException, NotEnoughMoneyException {
        MoneyTransactionVO vo = new MoneyTransactionVO();
        vo.setAccountId(accountId);
        vo.setType(type);
        vo.setAmount(amount);
        moneyTransactionService.create(vo);
    }


}
