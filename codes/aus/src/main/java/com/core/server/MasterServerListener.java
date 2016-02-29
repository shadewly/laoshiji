package com.core.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.core.handler.BaseChannelInitializer;

public class MasterServerListener implements ServletContextListener {

	

	public void start() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(3);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					// .localAddress(new InetSocketAddress("127.0.0.1", 8090))
					.localAddress(new InetSocketAddress("localhost", 8449))
					.option(ChannelOption.SO_BACKLOG, 100)
					// .childHandler(new
					// SsfChannelInitializer(applicationContext));
					.childHandler(
							new BaseChannelInitializer());
			ChannelFuture f = b.bind().sync();

			System.out.println(MasterServerListener.class.getName()
					+ "started and listen on" + f.channel().localAddress());

			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 启动netty
		try {
			Thread t = new Thread(new Runnable() {
				public void run() {
					try {
						new MasterServerListener().start();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			t.start();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) throws Exception {
		// 启动netty
		new MasterServerListener().start();
	}
}
