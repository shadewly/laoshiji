package com.demo.netty;

import com.demo.pojo.RPCRequest;
import com.demo.pojo.RPCResponse;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class RPCProxy {

    private String serverAddress;
    private ServiceDiscovery serviceDiscovery;

    public RPCProxy(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    public RPCProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
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

                        if (serviceDiscovery != null) {
                            serverAddress = serviceDiscovery.discover(); // 发现服务
                        }
                        System.out.println("Discovery service detected!");
                        String[] array = serverAddress.split(":");
                        String host = array[0];
                        int port = Integer.parseInt(array[1]);

                        RPCClient client = new RPCClient(host, port); // 初始化 RPC 客户端
                        RPCResponse response = client.send(request); // 通过 RPC 客户端发送 RPC 请求并获取 RPC 响应

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
