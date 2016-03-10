package com.test;

import java.io.Serializable;

/**
 * Created by Wang Linyong on 2016/3/10.
 */
public abstract class BaseRepository<M extends Serializable> {
    public void save(M m) {
        System.out.println("=====repository save:" + m);
    }
}