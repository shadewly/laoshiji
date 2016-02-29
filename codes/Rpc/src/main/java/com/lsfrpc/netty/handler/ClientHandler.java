package com.lsfrpc.netty.handler;

import com.lsfrpc.pojo.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wang LinYong on 2016-02-29.
 */
public class ClientHandler extends SimpleChannelInboundHandler<RPCResponse> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, RPCResponse msg) throws Exception {
        System.out.println("lock obj when received!");
            System.out.println("Thread " + Thread.currentThread().getName() + " notify all after receive response!");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("client caught exception", cause);
        ctx.close();
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    System.out.println("send ping to server----------");
                    break;
                default:
                    break;
            }
        }
    }
}
