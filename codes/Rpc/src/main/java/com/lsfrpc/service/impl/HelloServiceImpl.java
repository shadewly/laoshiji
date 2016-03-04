package com.lsfrpc.service.impl;

import com.lsfrpc.annotation.RPCComponent;
import com.lsfrpc.service.HelloService;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
@RPCComponent(HelloService.class) // 指定远程接口
public class HelloServiceImpl implements HelloService {
    @Override
    public String hello(String name) {
        return "Hello 余鑫晨,your nick name is ! " + name;
    }

    @Override
    public String handShake(String name) {
        return "Handshaking with " + name;
    }
}
