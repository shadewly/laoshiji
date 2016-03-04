package com.bz.master.controller;

import com.lsfrpc.bean.RPCBeanManager;
import com.lsfrpc.service.HelloService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-rpc-client.xml")
public class ClientTest {
    @Autowired
    private RPCBeanManager manager;

    @Test
    public void helloTest() {
        int i = 0;
        HelloService helloService = manager.getBean(HelloService.class);
        for (; i < 10; i++) {
            Long t = System.currentTimeMillis();
            String result = helloService.hello("World" + i);
            System.out.println(result);
//        result = helloService.handShake("Li lei" + i);
//        System.out.println(result);
            System.out.println("===>time cost>" + (System.currentTimeMillis() - t));
//            1.寻找channel发送请求策略
//                    2.阻塞没个serverChannel的线程
        }
    }
}
