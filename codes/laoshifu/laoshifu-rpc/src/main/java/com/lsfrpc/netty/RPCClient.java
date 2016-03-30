package com.lsfrpc.netty;

import com.lsfrpc.netty.channel.RPCChannel;
import com.lsfrpc.netty.encoder.RPCDecoder;
import com.lsfrpc.netty.encoder.RPCEncoder;
import com.lsfrpc.netty.handler.ClientHandler;
import com.lsfrpc.netty.zookeeper.ServiceDiscovery;
import com.lsfrpc.pojo.RPCRequest;
import com.lsfrpc.pojo.RPCResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class RPCClient {

    private static final Logger logger = LoggerFactory.getLogger(RPCClient.class);
    private Bootstrap bootstrap = new Bootstrap();
    /*当前active socketChannel池*/
    private ConcurrentHashMap<ChannelId, RPCChannel> socketChannelMap = new ConcurrentHashMap();
    private String[] serverAddresses = new String[0];
    private int threadNum;
    private int reconnectInterval = 2;
    private Map<SocketAddress, Object> reconnectQueue = new ConcurrentHashMap<>();
    private AtomicInteger index = new AtomicInteger();
    private ServiceDiscovery serviceDiscovery;

    public RPCClient(String... serverAddresses) {
        this.serverAddresses = serverAddresses;
        threadNum = serverAddresses.length;
        launch();
    }

    public RPCClient(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
        while (serverAddresses != null && serverAddresses.length == 0) {
            serverAddresses = serviceDiscovery.discoverList().toArray(serverAddresses); // 发现服务
        }
        threadNum = serverAddresses.length;
        launch();
    }

    /**
     * 启动服务
     */
    private void launch() {
        initial();
        for (String address : serverAddresses) {
            String[] array = address.split(":");
            String host = array[0];
            int port = Integer.parseInt(array[1]);
            startUp(new InetSocketAddress(host, port));
        }
        /*重连连接失败的接口*/
        reconnect();
        logger.info("Launch client done!");
    }

    /**
     * 初始化客户端
     */
    private void initial() {
        try {
            EventLoopGroup group = new NioEventLoopGroup();
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline()
//                            private final static int readerIdleTimeSeconds = 40;//读操作空闲30秒
//                            private final static int writerIdleTimeSeconds = 50;//写操作空闲60秒
//                            private final static int allIdleTimeSeconds = 100;//读写全部空闲100秒
                                    .addLast(new IdleStateHandler(20, 10, 0))
                                    .addLast(new RPCEncoder(RPCRequest.class)) // 将 RPC 请求进行编码（为了发送请求）
                                    .addLast(new RPCDecoder(RPCResponse.class)) // 将 RPC 响应进行解码（为了处理响应）
                                    .addLast(new ClientHandler(RPCClient.this)); // 使用 RPCClient 发送 RPC 请求
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Initial Channel error!", e);
            System.exit(-1);
        }
    }

    /**
     * 启动重连
     */
    private synchronized void reconnect() {
        while (!reconnectQueue.isEmpty()) {
            reconnectQueue.keySet().iterator().forEachRemaining(socketAddress -> {
                try {
                    Thread.currentThread().sleep(reconnectInterval * 1000);
                } catch (InterruptedException e) {
                }
                startUp(socketAddress);
                logger.debug("Reconnecting socketAddress[{}] and sleep for seconds!", socketAddress);
                startUp(socketAddress);
            });
        }
    }

    /**
     * 启动客户端
     *
     * @param serverSocketAddress
     */
    public void startUp(SocketAddress serverSocketAddress) {
        try {
            ChannelFuture future = bootstrap.connect(serverSocketAddress).sync();
            future.channel().pipeline().addLast(new ChannelHandlerAdapter() {
                @Override
                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                    super.channelInactive(ctx);
                    reconnectQueue.putIfAbsent(ctx.channel().remoteAddress(), RPCClient.this);
                    socketChannelMap.remove(ctx.channel().id());
                    reconnect();
                }
            });

            if (future.isSuccess()) {
                logger.debug("connect server[{}]  success!", serverSocketAddress);
                RPCChannel rpcChannel = new RPCChannel(this, future.channel());
                socketChannelMap.put(rpcChannel.id(), rpcChannel);
                if (reconnectQueue.containsKey(serverSocketAddress)) {
                    reconnectQueue.remove(serverSocketAddress);
                }
            } else {
                //加入失败队列
                logger.debug("Connect server[{}] failed, add into reconnectQueue!", serverSocketAddress);
                reconnectQueue.putIfAbsent(serverSocketAddress, RPCClient.this);
            }
        } catch (Exception e) {
            reconnectQueue.putIfAbsent(serverSocketAddress, RPCClient.this);
            logger.error("Connect {} error!", serverSocketAddress);
        }
    }

    public ConcurrentHashMap<ChannelId, RPCChannel> getSocketChannelMap() {
        return socketChannelMap;
    }

    public RPCResponse send(RPCRequest request) {
        ChannelId[] ids = new ChannelId[0];
        if (socketChannelMap == null || socketChannelMap.isEmpty()) {
            throw new NullPointerException("No connection!");
        }
        ids = socketChannelMap.keySet().toArray(ids);
        RPCChannel rpcChannel = this.socketChannelMap.get(ids[index.getAndIncrement() % threadNum]);
        return rpcChannel.send(request);
    }

}
