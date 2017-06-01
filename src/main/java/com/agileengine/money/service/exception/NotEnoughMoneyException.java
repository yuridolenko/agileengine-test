package com.agileengine.money.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by ded on 01.06.2017.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Not enough money")
public class NotEnoughMoneyException extends Exception {
}
