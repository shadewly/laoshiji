package com.demo.service.impl;

import com.demo.annotation.RPCService;
import com.demo.service.HelloService;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
@RPCService(HelloService.class) // 指定远程接口
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }
}
