package com.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import com.core.handler.DispatcherServletChannelInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.util.WebAppRootListener;

public class MasterServer {
    private WebApplicationContext applicationContext;

    public MasterServer() {
        initSpring();
    }

    private void initSpring() {
        XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
        applicationContext.setConfigLocations("classpath*:spring/spring-mvc.xml", "classpath*:spring/spring-core.xml", "classpath*:spring/spring-jdbc.xml");
        MockServletContext servletContext = new MockServletContext();
        MockServletConfig servletConfig = new MockServletConfig(servletContext);
        applicationContext.setServletConfig(servletConfig);
        applicationContext.refresh();
        this.applicationContext = applicationContext;
    }

    public void start() throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup(3);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress("127.0.0.1", 8080))
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .childHandler(new DispatcherServletChannelInitializer(applicationContext));
            ChannelFuture f = b.bind().sync();

            System.out.println(MasterServer.class.getName()
                    + "started and listen on" + f.channel().localAddress());

            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        // 启动netty
        new MasterServer().start();
    }
}
