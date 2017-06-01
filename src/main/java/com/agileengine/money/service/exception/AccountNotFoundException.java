package com.agileengine.money.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ded on 01.06.2017.
 */
@ResponseStatus(value = HttpStatus.NO_CONTENT, reason = "Account not found")
public class AccountNotFoundException extends Exception {
}
