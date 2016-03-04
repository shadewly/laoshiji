package com.lsfrpc.netty.future;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * Created by Wang Linyong on 2016/3/2.
 */
public class InvokeFuture<V> {
    private static Logger logger = LoggerFactory.getLogger(InvokeFuture.class);
    protected V result;
    protected Semaphore semaphore = new Semaphore(0);
    protected Throwable cause;


    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }

    public V getResult(long timeout, TimeUnit unit) {
        try {
            if (!semaphore.tryAcquire(timeout, unit)) {
                setCause(new Exception("time out."));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("Getting result error!", e);
        }
        return result;
    }

    public V getResult() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("Getting result error!", e);
        }
        return result;
    }

    public void setResult(V result) {
        semaphore.release(1);
        this.result = result;
    }
}
