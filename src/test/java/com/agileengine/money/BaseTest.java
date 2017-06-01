package com.agileengine.money;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by ded on 01.06.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseTest {

    public String uuid() {
        return UUID.randomUUID().toString();
    }
}
