package com.lsfrpc.netty.channel;


import com.lsfrpc.netty.future.InvokeFuture;
import com.lsfrpc.pojo.RPCRequest;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Wang Linyong on 2016/3/1.
 */
public class RPCChannel {
    private static Logger logger = LoggerFactory.getLogger(RPCChannel.class);
    private Channel channel;
    private Map<ChannelId, InvokeFuture> futureMap = new ConcurrentHashMap<>();
    private ChannelGroup channelGroup;

    public InvokeFuture send(RPCRequest request) throws Exception {
        ChannelFuture channelFuture = channel.writeAndFlush(request).addListener(future -> {
            if (future.isSuccess()) {
                logger.debug("Channel[{}] send request success!", channel);
            } else {
                logger.debug("Channel[{}] send request failed! caused by {}", channel, future.cause());
                future.cause().printStackTrace();
            }
        });
        return null;
    }
}
