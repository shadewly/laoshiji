package com.lsfrpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Wang LinYong on 2016-02-17.
 * 标识可被用作远程调用的类
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component // 表明可被 Spring 扫描
public @interface RPCComponent {
    /**
     * 注册远程服务的类名 for example:@RPCComponent(A.class)
     * interface A{}
     * class B implement A{}
     *
     * @return return represent class
     */
    Class value();
}

