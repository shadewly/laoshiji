package com.lsfrpc.netty.future;

import io.netty.channel.Channel;

import java.util.concurrent.Semaphore;

/**
 * Created by Wang Linyong on 2016/3/2.
 */
public class InvokeFuture<V> {
    protected V result;
    protected Semaphore semaphore = new Semaphore(0);
    protected Throwable cause;
    protected Channel channel;


}
