package com.netty.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

/**
 * 心跳链路监测
 * 作用：1.维持与客户端链接
 * 
 * @author Administrator
 *
 */
public class HeartBeatHandler extends ChannelHandlerAdapter {

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt)
			throws Exception {
		if (evt instanceof IdleStateEvent) {
			IdleStateEvent e = (IdleStateEvent) evt;
			if (e.state() == IdleState.READER_IDLE) {
				ctx.close();
				System.out.println("READER_IDLE 读超时");
			} else if (e.state() == IdleState.WRITER_IDLE) {
				ByteBuf buff = ctx.alloc().buffer();
				buff.writeBytes("mayi test".getBytes());
				ctx.writeAndFlush(buff);
				System.out.println("WRITER_IDLE 写超时");
			}

		}
	}

}
