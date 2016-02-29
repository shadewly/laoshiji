package com.lsfrpc.netty.proxy;

import com.lsfrpc.pojo.RPCRequest;
import com.lsfrpc.pojo.RPCResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.socket.SocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wang LinYong on 2016-02-29.
 */
public class RequestLautcher {
    private static Logger logger = LoggerFactory.getLogger(RequestLautcher.class);
    private SocketChannel socketChannel;

    public RPCResponse send(RPCRequest request) throws Exception {
        ChannelFuture channelFuture = socketChannel.writeAndFlush(request).addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.debug("Channel[{}] send request success!", socketChannel);
                } else {
                    logger.debug("Channel[{}] send request failed! caused by {}", socketChannel, future.cause());
                    future.cause().printStackTrace();
                }
            }
        });
        return response;
    }

}
