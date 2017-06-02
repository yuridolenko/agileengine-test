package com.agileengine.money.controller;

import com.agileengine.money.service.IMoneyTransactionService;
import com.agileengine.money.service.exception.AccountNotFoundException;
import com.agileengine.money.service.exception.NotEnoughMoneyException;
import com.agileengine.money.vo.IdVO;
import com.agileengine.money.vo.MoneyTransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by ded on 01.06.2017.
 */
@RestController
@RequestMapping("/api/transactions")
public class MoneyTransactionController {

    @Autowired
    private IMoneyTransactionService moneyTransactionService;

    @GetMapping
    public List<MoneyTransactionVO> read() {
        return moneyTransactionService.read();
    }

    @PutMapping
    public IdVO create(@RequestBody MoneyTransactionVO vo) throws AccountNotFoundException, NotEnoughMoneyException {
        Long transactionId = moneyTransactionService.create(vo);
        return new IdVO(transactionId);
    }

}
