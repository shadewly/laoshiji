package com.test;

/**
 * Created by Wang Linyong on 2016/3/10.
 */
public abstract class AbstractTestHello<T> implements TestInterface<T> {
    @Override
    public abstract void hello(T t);
}
