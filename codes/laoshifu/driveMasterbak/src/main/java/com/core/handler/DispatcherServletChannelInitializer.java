package com.core.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;

/**
 *
 */
public class DispatcherServletChannelInitializer extends
        ChannelInitializer<SocketChannel> {

    private final DispatcherServlet dispatcherServlet;

    public DispatcherServletChannelInitializer(WebApplicationContext applicationContext) throws ServletException {
        MockServletConfig config = new MockServletConfig();
        this.dispatcherServlet = new DispatcherServlet(applicationContext);
        this.dispatcherServlet.init(config);
    }

    @Override
    public void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();
        // Uncomment the following line if you want HTTPS
        // SSLEngine engine =
        // SecureChatSslContextFactory.getServerContext().createSSLEngine();
        // engine.setUseClientMode(false);
        // pipeline.addLast("ssl", new SslHandler(engine));
        pipeline.addLast("decoder", new HttpRequestDecoder());
        pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
        pipeline.addLast("encoder", new HttpResponseEncoder());
        pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
        pipeline.addLast("handler", new ServletNettyHandler(
                this.dispatcherServlet));

    }

}
