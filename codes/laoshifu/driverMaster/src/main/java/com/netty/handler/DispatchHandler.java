package com.netty.handler;

import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpResponseStatus.INTERNAL_SERVER_ERROR;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.springframework.mock.web.MockHttpServletResponse;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.DefaultHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.stream.ChunkedStream;
import io.netty.util.CharsetUtil;

public class DispatchHandler extends
		SimpleChannelInboundHandler<FullHttpRequest> {

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		cause.printStackTrace();
		if (ctx.channel().isActive()) {
			sendError(ctx, INTERNAL_SERVER_ERROR);
		}
	}

	private static void sendError(ChannelHandlerContext ctx,
			HttpResponseStatus status) {
		ByteBuf content = Unpooled.copiedBuffer("Failure: " + status.toString()
				+ "\r\n", CharsetUtil.UTF_8);

		FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(
				HTTP_1_1, status, content);
		fullHttpResponse.headers().add(CONTENT_TYPE,
				"text/plain; charset=UTF-8");

		// Close the connection as soon as the error message is sent.
		ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext channelHandlerContext,
			FullHttpRequest fullHttpRequest) throws Exception {
		MockHttpServletResponse servletResponse = new MockHttpServletResponse();
		HttpResponseStatus status = HttpResponseStatus.valueOf(servletResponse
				.getStatus());
		HttpResponse response = new DefaultHttpResponse(HTTP_1_1, status);
		System.out.println("555555555555555555555");
		channelHandlerContext.write(response);

		InputStream contentStream = new ByteArrayInputStream(
				servletResponse.getContentAsByteArray());

		// Write the content and flush it.
		ChannelFuture writeFuture = channelHandlerContext
				.writeAndFlush(new ChunkedStream(contentStream));
		writeFuture.addListener(ChannelFutureListener.CLOSE);
		fullHttpRequest.retain();

	}

}
