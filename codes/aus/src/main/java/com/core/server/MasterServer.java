package com.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.core.handler.BaseChannelInitializer;

public class MasterServer {
	private WebApplicationContext applicationContext;

	public MasterServer() {
		initSpring();
	}

	private void initSpring() {
		XmlWebApplicationContext applicationContext = new XmlWebApplicationContext();
		applicationContext
				.setConfigLocations("classpath*:spring/spring-core.xml");
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
//					.localAddress(new InetSocketAddress("127.0.0.1", 8090))
					.localAddress(new InetSocketAddress("localhost", 8444))
					.option(ChannelOption.SO_BACKLOG, 100)
					.childHandler(new BaseChannelInitializer(applicationContext));
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
