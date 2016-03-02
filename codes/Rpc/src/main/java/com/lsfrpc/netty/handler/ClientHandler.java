package com.lsfrpc.netty.handler;

import com.lsfrpc.netty.RPCClient;
import com.lsfrpc.netty.channel.RPCChannel;
import com.lsfrpc.netty.future.InvokeFuture;
import com.lsfrpc.pojo.RPCResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wang LinYong on 2016-02-29.
 */
public class ClientHandler extends SimpleChannelInboundHandler<RPCResponse> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
    private RPCClient client;

    public ClientHandler(RPCClient client) {
        this.client = client;
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, RPCResponse msg) throws Exception {
        ChannelId id = ctx.channel().id();
        RPCChannel rpcChannel = client.getSocketChannelMap().get(id);
        InvokeFuture<RPCResponse> invokeFuture = rpcChannel.getFuture(id);
        invokeFuture.setResult(msg);
        rpcChannel.removeFuture(id);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("client caught exception", cause);
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
