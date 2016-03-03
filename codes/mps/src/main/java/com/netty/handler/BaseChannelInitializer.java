package com.netty.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

/**
 *
 */
public class BaseChannelInitializer extends ChannelInitializer<SocketChannel> {
	private final static int readerIdleTimeSeconds = 5;// 读操作空闲30秒
	private final static int writerIdleTimeSeconds = 10;// 写操作空闲60秒
	private final static int allIdleTimeSeconds = 20;// 读写全部空闲100秒

	@Override
	public void initChannel(SocketChannel channel) throws Exception {
		// 待解决问题，cas登录后不跳转，可能是证书问题
		ChannelPipeline pipeline = channel.pipeline();

		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
		// 闲置心跳handler
		pipeline.addLast("idleStateHandler", new IdleStateHandler(
				readerIdleTimeSeconds, writerIdleTimeSeconds,
				allIdleTimeSeconds));
//		pipeline.addLast("idleStateHandler", new IdleStateHandler(
//				pipeline.addLast("heartBeatHandler" ,new HeartBeatHandler())));
		pipeline.addLast("heartBeatHandler" ,new HeartBeatHandler());

	}

}
