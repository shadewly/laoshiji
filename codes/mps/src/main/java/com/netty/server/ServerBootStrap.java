package com.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

import com.netty.handler.BaseChannelInitializer;

public class ServerBootStrap {

	public void start() throws InterruptedException {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(3);
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.localAddress(new InetSocketAddress("localhost", 8450))
					.option(ChannelOption.TCP_NODELAY,true)//设置封包 使用一次大数据的写操作，而不是多次小数据的写操作
					.option(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT)//AdaptiveRecvByteBufAllocator可变大小
					.option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)//使用内存池
					.childHandler(new BaseChannelInitializer());
			
			 
			ChannelFuture f = b.bind().sync();

			System.out.println(ServerBootStrap.class.getName()
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
		new ServerBootStrap().start();
	}
}
