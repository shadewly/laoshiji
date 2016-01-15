package com.core.handler;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

import javax.servlet.ServletException;

import org.springframework.mock.web.MockServletConfig;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import com.core.server.SpringMvcConfig;

public class DispatcherServletChannelInitializer extends
		ChannelInitializer<SocketChannel> {

	private final DispatcherServlet dispatcherServlet;

	public DispatcherServletChannelInitializer() throws ServletException {

		MockServletContext servletContext = new MockServletContext();
		MockServletConfig servletConfig = new MockServletConfig(servletContext);
		// 注释部分为xml加载springMVC方式
		// servletConfig.addInitParameter("contextConfigLocation",
		// "classpath*:spring/spring-core.xml");
		// servletContext.addInitParameter("contextConfigLocation",
		// "classpath*:spring/spring-core.xml");

		AnnotationConfigWebApplicationContext wac = new AnnotationConfigWebApplicationContext();
		// XmlWebApplicationContext wac = new
		// XmlWebApplicationContext();//采用xml的配置形式加载spring文件，初始化springmvc的context

		wac.setServletContext(servletContext);
		wac.setServletConfig(servletConfig);
		// wac.setConfigLocation("classpath*:spring/spring-mvc.xml");
		wac.register(SpringMvcConfig.class);
		wac.refresh();

		this.dispatcherServlet = new DispatcherServlet(wac);
		this.dispatcherServlet.init(servletConfig);

	}

	@Override
	public void initChannel(SocketChannel channel) throws Exception {
		// Create a default pipeline implementation.
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
