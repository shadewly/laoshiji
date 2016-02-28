package com.lsfrpc.netty;

import com.lsfrpc.netty.zookeeper.ServiceDiscovery;
import com.lsfrpc.pojo.RPCRequest;
import com.lsfrpc.pojo.RPCResponse;
import com.lsfrpc.netty.encoder.RPCDecoder;
import com.lsfrpc.netty.encoder.RPCEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Wang LinYong on 2016-02-17.
 */
public class RPCClient extends SimpleChannelInboundHandler<RPCResponse> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RPCClient.class);

    private String host;
    private int port;
    private SocketChannel socketChannel;
    private RPCResponse response;
    private ServiceDiscovery serviceDiscovery;

    private final Object obj = new Object();

    public RPCClient(String host, int port) {
        this.host = host;
        this.port = port;
        initial();
    }

    public RPCClient(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
        initial();
    }

    private void initial() {
        try {
            EventLoopGroup group = new NioEventLoopGroup();
            Bootstrap bootstrap = new Bootstrap();
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
                                    .addLast(RPCClient.this); // 使用 RPCClient 发送 RPC 请求
                        }
                    })
                    .option(ChannelOption.SO_KEEPALIVE, true);

            if (serviceDiscovery != null) {
                String serverAddress = serviceDiscovery.discover(); // 发现服务
                String[] array = serverAddress.split(":");
                host = array[0];
                port = Integer.parseInt(array[1]);
            }

            ChannelFuture future = bootstrap.connect(host, port).sync();
            if (future.isSuccess()) {
                System.out.println("connect server  成功---------");
                socketChannel = (SocketChannel) future.channel();
            }
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Connect server error!", e);
            System.exit(-1);
        }
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case WRITER_IDLE:
                    System.out.println("send ping to server----------");
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, RPCResponse response) throws Exception {
        this.response = response;
        System.out.println("lock obj when received!");
        synchronized (obj) {
            System.out.println("Thread " + Thread.currentThread().getName() + " notify all after receive response!");
            obj.notifyAll(); // 收到响应，唤醒线程
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("client caught exception", cause);
        ctx.close();
    }

    public RPCResponse send(RPCRequest request) throws Exception {

        System.out.println("lock obj when send!");
        synchronized (obj) {
//            socketChannel.writeAndFlush(request).sync();
            System.out.println("Thread " + Thread.currentThread().getName() + ",and socketChannel[" + socketChannel + "]waiting before send request!");
            socketChannel.writeAndFlush(request).addListener(new ChannelFutureListener() {

                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("Client>>write and flush over:" + future.isSuccess());
                    } else {
                        System.out.println("error================>:" + future.isSuccess());
                        future.cause().printStackTrace();
                        future.channel().close();
                    }
                }
            });
            System.out.println("send msg end!");
            obj.wait(); // 未收到响应，使线程等待
        }

        if (response != null) {
//            socketChannel.closeFuture().sync();
        }
        return response;
    }

}
