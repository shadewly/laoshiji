package com.lsfrpc.netty;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class RPCBootstrap {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("spring/spring-rpc-server.xml");
    }
}
