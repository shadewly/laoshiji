package com.lsfrpc.netty.proxy;

import com.lsfrpc.netty.RPCClient;
import com.lsfrpc.pojo.RPCRequest;
import com.lsfrpc.pojo.RPCResponse;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class RPCProxy {

    @Autowired
    private RPCClient rpcClient;

    public RPCProxy(RPCClient rpcClient) {
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<?> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        RPCRequest request = new RPCRequest(); // 创建并初始化 RPC 请求
                        request.setRequestId(UUID.randomUUID().toString());
                        request.setClassName(method.getDeclaringClass().getName());
                        request.setMethodName(method.getName());
                        request.setParameterTypes(method.getParameterTypes());
                        request.setParameters(args);

                        RPCResponse response = rpcClient.send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应

                        if (response.isError()) {
                            throw response.getError();
                        } else {
                            return response.getResult();
                        }
                    }
                }
        );
    }
}
