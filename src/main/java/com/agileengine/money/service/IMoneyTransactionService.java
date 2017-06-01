package com.agileengine.money.service;

import com.agileengine.money.service.exception.AccountNotFoundException;
import com.agileengine.money.service.exception.NotEnoughMoneyException;
import com.agileengine.money.vo.MoneyTransactionVO;

import java.util.List;

/**
 * Created by ded on 01.06.2017.
 */
public interface IMoneyTransactionService {

    List<MoneyTransactionVO> read();

    Long create(MoneyTransactionVO vo) throws AccountNotFoundException, NotEnoughMoneyException;
}
