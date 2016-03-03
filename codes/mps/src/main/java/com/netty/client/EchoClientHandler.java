package com.netty.client;

import java.net.SocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
	ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
	ctx.flush();
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, ByteBuf in) {

		System.out.println("Client received:"
				+ ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress,
			SocketAddress localAddress, ChannelPromise promise)
			throws Exception {
		System.out.println("Hello world, I'm client.");  
		super.connect(ctx, remoteAddress, localAddress, promise);
	}

}
