package com.lsfrpc.netty.proxy;

import com.lsfrpc.pojo.RPCRequest;
import com.lsfrpc.pojo.RPCResponse;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * Created by Wang LinYong on 2016-02-29.
 */
public class RequestLautcher {
    public RPCResponse send(RPCRequest request) throws Exception {

        System.out.println("lock obj when send!");
//            socketChannel.writeAndFlush(request).sync();
        System.out.println("Thread " + Thread.currentThread().getName() + ",and socketChannel[" + socketChannel + "]waiting before send request!");
        socketChannel.writeAndFlush(request).addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    System.out.println("Client>>write and flush over:" + future.isSuccess());
                } else {
                    System.out.println("error================>:" + future.isSuccess());
                    future.cause().printStackTrace();
                    future.channel().close();
                }
            }
        });
        System.out.println("send msg end!");

        if (response != null) {
//            socketChannel.closeFuture().sync();
        }
        return response;
    }

}
