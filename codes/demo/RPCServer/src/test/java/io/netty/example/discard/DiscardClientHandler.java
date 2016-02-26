/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.example.discard;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Handles a client-side channel.
 */
public class DiscardClientHandler extends SimpleChannelInboundHandler<Object> {

    private ByteBuf content;
    private ChannelHandlerContext ctx;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("DiscardClientHandler->channelActive");
        this.ctx = ctx;

        // Initialize the message.
        content = ctx.alloc().directBuffer(DiscardClient.SIZE).writeZero(DiscardClient.SIZE);

        // Send the initial messages.
        generateTraffic();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        content.release();
    }

    @Override
    public void messageReceived(ChannelHandlerContext ctx, Object msg) {
        System.out.println("DiscardClientHandler->messageReceived:"+msg);
        // Server is supposed to send nothing, but if it sends something, discard it.
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        System.out.println("DiscardClientHandler->exceptionCaught");
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }

    long counter;

    private void generateTraffic() {
        System.out.println("DiscardClientHandler->generateTraffic");
        // Flush the outbound buffer to the socket.
        // Once flushed, generate the same amount of traffic again.
        ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);
        ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);
        ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);
        ctx.writeAndFlush(content.duplicate().retain()).addListener(trafficGenerator);

    }

    private final ChannelFutureListener trafficGenerator = new ChannelFutureListener() {
        public void operationComplete(ChannelFuture future) {
            System.out.println("DiscardClientHandler->operationComplete:"+future.isSuccess());
            if (future.isSuccess()) {
//                generateTraffic();
                System.out.println("Client>>write and flush over:"+future.isSuccess());
            } else {
                future.cause().printStackTrace();
                future.channel().close();
            }
        }
    };
}
