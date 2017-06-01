package com.agileengine.money.service;

import com.agileengine.money.vo.AccountVO;

import java.util.List;

/**
 * Created by ded on 01.06.2017.
 */
public interface IAccountService {

    List<AccountVO> read();
}
