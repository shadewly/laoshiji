package com.test;

import org.springframework.stereotype.Service;

/**
 * Created by Wang Linyong on 2016/3/10.
 */
@Service
public class TestHello2 extends AbstractTestHello<Object> {
    @Override
    public void hello(Object s) {
        System.out.println("in string!!!!");
    }
}
