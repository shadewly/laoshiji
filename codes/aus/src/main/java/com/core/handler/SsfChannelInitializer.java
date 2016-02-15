package com.core.handler;

import java.io.File;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.servlet.ServletException;

import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 */
public class SsfChannelInitializer extends ChannelInitializer<SocketChannel> {
	private final DispatcherServlet dispatcherServlet;
	private final DelegatingFilterProxy delegatingFilterProxy;

	public SsfChannelInitializer(WebApplicationContext applicationContext)
			throws ServletException {
		MockFilterConfig config = new MockFilterConfig();
		this.delegatingFilterProxy = new DelegatingFilterProxy(
				"springSecurityFilterChain", applicationContext);
		// this.delegatingFilterProxy = new DelegatingFilterProxy(
		// "authenticationFilter", applicationContext);
		this.delegatingFilterProxy.init(config);

		// spring mvc
		MockServletConfig servletConfig = new MockServletConfig();
		this.dispatcherServlet = new DispatcherServlet(applicationContext);
		this.dispatcherServlet.init(servletConfig);

	}

	@Override
	public void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		// Uncomment the following line if you want HTTPS
		// SSLEngine engine =
		// SecureChatSslContextFactory.getServerContext().createSSLEngine();
		// engine.setUseClientMode(false);
		// pipeline.addLast("ssl", new SslHandler(engine));
		// Uncomment the following line if you want HTTPS
		File certChainFile = ResourceUtils
				.getFile("classpath:certifications/cas_cert.pem");
		com.common.util.Assert.notNull(certChainFile,
				"Certification file can't be null!");
		File keyFile = ResourceUtils
				.getFile("classpath:certifications/privateKey_pkcs8.pem");
		com.common.util.Assert.notNull(keyFile, "Key file can't be null!");
		SslContext sslCtx = SslContext.newServerContext(certChainFile, keyFile);
		pipeline.addLast("ssl",
				new SslHandler(sslCtx.newEngine(channel.alloc())));
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
		pipeline.addLast("ssfHandler", new SsfHandler(this.delegatingFilterProxy));
		pipeline.addLast("springMvcHandler", new ServletNettyHandler(
				this.dispatcherServlet));

	}

}
