package com.demo.service.impl;

import com.demo.annotation.RPCComponent;
import com.demo.service.HelloService;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
@RPCComponent(HelloService.class) // 指定远程接口
public class HelloServiceImpl implements HelloService {

    @Override
    public String hello(String name) {
        return "Hello! " + name;
    }

    @Override
    public String handShake(String name) {
        return "Handshaking with " + name;
    }
}
