package com.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import com.core.handler.DispatcherServletChannelInitializer;

public class MasterServer {

	public void start() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {

			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress("127.0.0.1", 8080))
					.option(ChannelOption.SO_BACKLOG, 100)
					.childHandler(new DispatcherServletChannelInitializer());
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
		// 加载spring核心配置文件
		SpringContainer.init();
		// 启动netty
		new MasterServer().start();
	}
}
