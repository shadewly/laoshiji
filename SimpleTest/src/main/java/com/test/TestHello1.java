package com.test;

import org.springframework.stereotype.Service;

/**
 * Created by Wang Linyong on 2016/3/10.
 */
@Service
public class TestHello1 extends AbstractTestHello<pojo1> {
    @Override
    public void hello(pojo1 pojo1) {
        System.out.println("--->" + pojo1.i);
    }
}
