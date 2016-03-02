package com.lsfrpc.netty.channel;


import com.lsfrpc.netty.RPCClient;
import com.lsfrpc.netty.future.InvokeFuture;
import com.lsfrpc.pojo.RPCRequest;
import com.lsfrpc.pojo.RPCResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by Wang Linyong on 2016/3/1.
 */
public class RPCChannel {
    private static Logger logger = LoggerFactory.getLogger(RPCChannel.class);
    private Channel channel;
    private RPCClient client;
    private Map<String, InvokeFuture> futureMap = new ConcurrentHashMap<>();
    private ChannelGroup channelGroup;

    public RPCChannel(RPCClient client, Channel channel) {
        this.client = client;
        this.channel = channel;
    }

    public ChannelId id() {
        return channel.id();
    }

    public InvokeFuture getFuture(ChannelId id) {
        return futureMap.get(id);
    }

    public InvokeFuture removeFuture(ChannelId id) {
        return futureMap.remove(id);
    }

    public RPCResponse send(RPCRequest request) {
        InvokeFuture<RPCResponse> invokeFuture = new InvokeFuture<>();
        futureMap.put(request.getRequestId(), invokeFuture);
        ChannelFuture channelFuture = channel.writeAndFlush(request).addListener(future -> {
            if (future.isSuccess()) {
                logger.debug("Channel[{}] send request success!", channel);
            } else {
                logger.debug("Channel[{}] send request failed! caused by {}", channel, future.cause());
                future.cause().printStackTrace();
            }
        });
        return invokeFuture.getResult(10, TimeUnit.SECONDS);
    }

    public ChannelGroup getChannelGroup() {
        return channelGroup;
    }

    public void setChannelGroup(ChannelGroup channelGroup) {
        this.channelGroup = channelGroup;
    }

    public RPCClient getClient() {
        return client;
    }

    public void setClient(RPCClient client) {
        this.client = client;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
