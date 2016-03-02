package com.lsfrpc.netty;

import com.lsfrpc.annotation.RPCComponent;
import com.lsfrpc.netty.encoder.RPCDecoder;
import com.lsfrpc.netty.encoder.RPCEncoder;
import com.lsfrpc.netty.handler.ServerHandler;
import com.lsfrpc.netty.zookeeper.ServiceRegistry;
import com.lsfrpc.pojo.RPCRequest;
import com.lsfrpc.pojo.RPCResponse;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class RPCServer implements ApplicationContextAware, InitializingBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(RPCServer.class);

    private String[] serverAddresses;
    private ServiceRegistry serviceRegistry;
    private EventLoopGroup bossGroup = new NioEventLoopGroup();
    private EventLoopGroup workerGroup = new NioEventLoopGroup();
    private ServerBootstrap bootstrap = new ServerBootstrap();
    private ConcurrentMap<String, Object> handlerMap = new ConcurrentHashMap<>(); // 存放接口名与服务对象之间的映射关系

    public RPCServer(String... serverAddresses) {
        this.serverAddresses = serverAddresses;
    }

    public RPCServer(ServiceRegistry serviceRegistry, String... serverAddresses) {
        this.serviceRegistry = serviceRegistry;
        this.serverAddresses = serverAddresses;
    }

    private void init() {
        bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel channel) throws Exception {
                        channel.pipeline()
                                .addLast(new RPCDecoder(RPCRequest.class)) // 将 RPC 请求进行解码（为了处理请求）
                                .addLast(new RPCEncoder(RPCResponse.class)) // 将 RPC 响应进行编码（为了返回响应）
                                .addLast(new ServerHandler(handlerMap)); // 处理 RPC 请求
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        Map<String, Object> serviceBeanMap = ctx.getBeansWithAnnotation(RPCComponent.class); // 获取所有带有 RPCComponent 注解的 Spring Bean
        if (MapUtils.isNotEmpty(serviceBeanMap)) {
            for (Object serviceBean : serviceBeanMap.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RPCComponent.class).value().getName();
                Object previousObj = handlerMap.putIfAbsent(interfaceName, serviceBean);
                if (previousObj != null) {
                    LOGGER.warn("Found replicate service {}, replaced", interfaceName);
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
        bind(this.serverAddresses);
    }

    public void shutDown() {
        workerGroup.shutdownGracefully();
        bossGroup.shutdownGracefully();
    }

    public void bind(String... serverAddresses) {
        for (String serverAddress : serverAddresses) {
            try {
                String[] array = serverAddress.split(":");
                String host = array[0];
                int port = Integer.parseInt(array[1]);

                ChannelFuture future = null;
                future = bootstrap.bind(host, port).sync();
                LOGGER.warn("Server started on port {}", port);

                if (serviceRegistry != null) {
                    LOGGER.info("Register service address [{}]!", serverAddress);
                    serviceRegistry.register(serverAddress); // 注册服务地址
                }
                if (future.isSuccess()) {
                    LOGGER.debug("Start server with address [{}] success!", serverAddress);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                LOGGER.error("Bind server error with address [{}]", serverAddress);
            }
        }
    }
}
