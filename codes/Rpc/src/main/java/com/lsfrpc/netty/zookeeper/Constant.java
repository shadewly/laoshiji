package com.lsfrpc.netty.zookeeper;

/**
 * Created by Administrator on 2016/2/28.
 */
public interface Constant {
    int ZK_SESSION_TIMEOUT = 5000;

    String ZK_REGISTRY_PATH = "/registry";
    String ZK_DATA_PATH = ZK_REGISTRY_PATH + "/server";
}
