package com.core.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import java.io.File;

import javax.servlet.ServletException;

import junit.framework.Assert;

import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

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
		File certChainFile = new File("D:\\keys\\mycas.crt");
		// Assert.notNull(certChainFile, "Certification file can't be null!");
		File keyFile = new File("D:\\keys\\mycas.keystore");
		// Assert.notNull(keyFile, "Key file can't be null!");
		SslContext sslCtx = SslContext.newServerContext(certChainFile, keyFile);

		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
		pipeline.addLast("ssl",
				new SslHandler(sslCtx.newEngine(channel.alloc())));
		pipeline.addLast("ssoHandler", new SsoHandler(
				this.delegatingFilterProxy));
		pipeline.addLast("springMvcHandler", new ServletNettyHandler(
				this.dispatcherServlet));

	}

}
