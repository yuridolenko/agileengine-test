package com.agileengine.money.service;

import com.agileengine.money.entity.Account;
import com.agileengine.money.entity.MoneyTransaction;
import com.agileengine.money.entity.TransactionType;
import com.agileengine.money.repository.AccountRepository;
import com.agileengine.money.repository.MoneyTransactionRepository;
import com.agileengine.money.service.exception.AccountNotFoundException;
import com.agileengine.money.service.exception.NotEnoughMoneyException;
import com.agileengine.money.vo.MoneyTransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ded on 01.06.2017.
 */
@Service
public class MoneyTransactionService implements IMoneyTransactionService{

    @Autowired
    private MoneyTransactionRepository moneyTransactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public List<MoneyTransactionVO> read() {
        List<MoneyTransactionVO> result = new ArrayList<>();
        Iterable<MoneyTransaction> transactions = moneyTransactionRepository.findAll();
        transactions.forEach(transaction -> result.add(new MoneyTransactionVO(transaction)));
        return result;
    }

    @Override
    public Long create(MoneyTransactionVO vo) throws AccountNotFoundException, NotEnoughMoneyException {
        Account account = accountRepository.findOne(vo.getAccountId());
        if (account == null) {
            throw new AccountNotFoundException();
        }
        if (TransactionType.CREDIT.equals(vo.getType()) && vo.getAmount() > account.getMoneyAmount()) {
            throw new NotEnoughMoneyException();
        }

        MoneyTransaction transaction = createAndProcessTransaction(account, vo);
        return transaction.getId();
    }

    private MoneyTransaction createAndProcessTransaction(Account account, MoneyTransactionVO vo) {
        MoneyTransaction transaction = moneyTransactionRepository.save(new MoneyTransaction(account, vo.getType(), vo.getAmount()));
        account.addMoney(vo.getSignedAmount());
        accountRepository.save(account);
        return transaction;
    }



}
