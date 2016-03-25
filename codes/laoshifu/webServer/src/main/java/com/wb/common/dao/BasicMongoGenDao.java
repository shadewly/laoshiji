package com.wb.common.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * Created by ThinkPad on 2016-01-21.
 */
public abstract class BasicMongoGenDao<T> {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());
//    @Autowired
    protected MongoTemplate mongoTemplate;

    /**
     * Save a generics object
     *
     * @param t
     * @return
     */
    public void save(T t) {
        this.mongoTemplate.save(t);
    }
}
