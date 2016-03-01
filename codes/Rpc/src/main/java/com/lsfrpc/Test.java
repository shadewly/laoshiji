package com.lsfrpc;

import io.netty.util.concurrent.ExecutorServiceFactory;

import java.util.concurrent.*;

/**
 * Created by Wang Linyong on 2016/3/2.
 */
public class Test {

    public static void main(String[] af) {
        Executor executor = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(0);
        for (int index = 0; index < 5; index++) {
            final int NO = index;
            Runnable run = new Runnable() {
                public void run() {
                    try {
                        // 获取许可
                        if (NO > 0) {
                            semaphore.acquire();
                        }
                        System.out.println("Accessing: " + NO);
                        Thread.sleep((long) (Math.random() * 6000));
                        // 访问完后，释放
                        System.out.println(NO + "-----------------" + semaphore.availablePermits());
                        semaphore.release();
                        //availablePermits()指的是当前信号灯库中有多少个可以被使用
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            executor.execute(run);
        }
    }
}
