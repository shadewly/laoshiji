package com.lsfrpc.bean;

import com.lsfrpc.netty.proxy.RPCProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Wang LinYong on 2016-03-04.
 */
@Component
public class RPCBeanManager {
    @Autowired
    private RPCProxy proxy;

    public RPCBeanManager(RPCProxy rpcProxy) {
    }

    public <V> V getBean(Class<V> vClass) {
        return proxy.create(vClass);
    }

}
