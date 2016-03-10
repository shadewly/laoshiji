package com.test;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * Created by Wang Linyong on 2016/3/10.
 */
public abstract class BaseService<M extends Serializable> {
    @Autowired
    protected BaseRepository<M> repository;

    public void save(M m) {
        repository.save(m);
    }
}



