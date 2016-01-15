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
					.localAddress(new InetSocketAddress("127.0.0.1", 8088))
					.option(ChannelOption.SO_BACKLOG, 100)
					.childHandler(new DispatcherServletChannelInitializer())
			// .childHandler(new ChannelInitializer<SocketChannel>() {
			// @Override
			// public void initChannel(SocketChannel ch)
			// throws IOException {
			// // ch.pipeline().addLast(new PacageDecoderHandler(1024 *
			// // 1024 * 1024, 0, 4));
			// // ch.pipeline().addLast(new PacageEncoderHandler());
			// //
			// ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingDecoder());
			// //
			// ch.pipeline().addLast(MarshallingCodeCFactory.buildMarshallingEncoder());
			// // // ch.pipeline().addLast(new ReadTimeoutHandler(5));
			// // ch.pipeline().addLast(new LoginHandler());
			// // ch.pipeline().addLast(new MessageDispatchHandler());
			// // ch.pipeline().addLast(new
			// // HaveReadMessageChatNotifyHandler());
			// // ch.pipeline().addLast(new UnReadMessageReqHandler());
			// // ch.pipeline().addLast(new
			// // HistoryMessageReqHandler());
			// // ch.pipeline().addLast(new
			// // ModifyUserInfoReqHandler());
			// // ch.pipeline().addLast(new
			// // ChatGroupMemberReqHandler());
			// }
			// }
			// )
			;
			ChannelFuture f = b.bind().sync();

			System.out.println(MasterServer.class.getName()
					+ "started and listen on" + f.channel().localAddress());

			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		SpringServer.init();
		new MasterServer().start();
	}
}
