package com.lsfrpc.netty.handler;

import com.lsfrpc.pojo.RPCRequest;
import com.lsfrpc.pojo.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import net.sf.cglib.reflect.FastClass;
import net.sf.cglib.reflect.FastMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class ServerHandler extends SimpleChannelInboundHandler<RPCRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerHandler.class);

    private final Map<String, Object> handlerMap;

    public ServerHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }
    
    private Object handle(RPCRequest request) throws Throwable {
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);

        Class<?> serviceClass = serviceBean.getClass();
        String methodName = request.getMethodName();
        Class<?>[] parameterTypes = request.getParameterTypes();
        Object[] parameters = request.getParameters();

        /*Method method = serviceClass.getMethod(methodName, parameterTypes);
        method.setAccessible(true);
        return method.invoke(serviceBean, parameters);*/

        FastClass serviceFastClass = FastClass.create(serviceClass);
        FastMethod serviceFastMethod = serviceFastClass.getMethod(methodName, parameterTypes);
        Object obj= serviceFastMethod.invoke(serviceBean, parameters);
        return obj;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        LOGGER.error("server caught exception", cause);
        System.out.print("server caught exception");
        ctx.close();
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, RPCRequest request) throws Exception {
        System.out.println("Receive message in server side");
        RPCResponse response = new RPCResponse();
        response.setRequestId(request.getRequestId());
        try {
            Object result = handle(request);
            response.setResult(result);
        } catch (Throwable t) {
            response.setError(t);
            t.printStackTrace();
        }
//        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
        ctx.writeAndFlush(response);
    }
}
