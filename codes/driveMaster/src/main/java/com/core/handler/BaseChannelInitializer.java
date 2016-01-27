package com.core.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;

/**
 *
 */
public class BaseChannelInitializer extends ChannelInitializer<SocketChannel> {

	private final DispatcherServlet dispatcherServlet;
	private final DelegatingFilterProxy delegatingFilterProxy;

	public BaseChannelInitializer(WebApplicationContext applicationContext)
			throws ServletException {
		// spring mvc
		MockServletConfig servletConfig = new MockServletConfig();
		this.dispatcherServlet = new DispatcherServlet(applicationContext);
		this.dispatcherServlet.init(servletConfig);

		// sso
		MockFilterConfig filterConfig = new MockFilterConfig();
		this.delegatingFilterProxy = new DelegatingFilterProxy(
				"authenticationFilter", applicationContext);
		this.delegatingFilterProxy.init(filterConfig);
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
		pipeline.addLast("ssoHandler", new SsoHandler(
				this.delegatingFilterProxy));
		pipeline.addLast("springMvcHandler", new ServletNettyHandler(
				this.dispatcherServlet));

	}

}
