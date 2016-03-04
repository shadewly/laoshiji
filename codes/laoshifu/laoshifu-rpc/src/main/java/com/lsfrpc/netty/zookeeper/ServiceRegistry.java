package com.lsfrpc.netty.zookeeper;

import org.apache.zookeeper.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class ServiceRegistry {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceRegistry.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private String registryAddress;

    private ZooKeeper zk;

    public ServiceRegistry(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public void register(String data) {
        if (zk == null) {
            zk = connectServer();
        }
        createNode(Constant.ZK_REGISTRY_PATH, data);
    }


    public void register(String path, String data) {
        if (data == null) {
            LOGGER.warn("Register null value for in ZooKeeper!");
        } else if (path == null) {
            throw new NullPointerException("path when register!");
        }
        if (zk == null) {
            zk = connectServer();
        }
        createNode(path, data);
    }

    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, Constant.ZK_SESSION_TIMEOUT, event -> {
                if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    latch.countDown();
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            LOGGER.error("Error when connect ZooKeeper server!", e);
        }
        return zk;
    }

    private void createNode(String path, String data) {
        try {
            byte[] bytes = data.getBytes();
            zk.create(Constant.ZK_DATA_PATH, bytes, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            LOGGER.debug("create zookeeper node ({} => {})", path, data);
        } catch (KeeperException | InterruptedException e) {
            LOGGER.error("Creating node on ZooKeeper server error!", e);
            e.printStackTrace();
        }
    }
}
