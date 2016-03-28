package com.dm.common.dao;

import java.util.List;

/**
 * Created by Wang Linyong on 2016/3/15.
 */
public interface GenericDao<E, PK> {

    List<E> findAll();

    E findByPK(PK pk);

    E save(E toPersist);

    E update(E toPersist);

}
