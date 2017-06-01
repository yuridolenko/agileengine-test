package com.agileengine.money.controller;

import com.agileengine.money.service.IAccountService;
import com.agileengine.money.vo.AccountVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ded on 01.06.2017.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @GetMapping
    public List<AccountVO> read() {
        return accountService.read();
    }
}
