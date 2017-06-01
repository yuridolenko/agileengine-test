package com.agileengine.money.repository;

import com.agileengine.money.entity.Account;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by ded on 01.06.2017.
 */
public interface AccountRepository extends CrudRepository<Account, Long> {
}
