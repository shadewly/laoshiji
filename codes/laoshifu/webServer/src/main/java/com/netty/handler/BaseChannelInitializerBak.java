package com.netty.handler;

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

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.springframework.mock.web.MockFilterConfig;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.util.ResourceUtils;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 */
public class BaseChannelInitializerBak extends ChannelInitializer<SocketChannel> {

	private final DispatcherServlet dispatcherServlet;
	private final DelegatingFilterProxy delegatingFilterProxy;
	private final SingleSignOutFilter singleSignOutFilter;
	private final SingleSignOutHttpSessionListener singleSignOutListener;

	public BaseChannelInitializerBak(WebApplicationContext applicationContext)
			throws ServletException {
		// sso
		MockFilterConfig singleSignOutFilterConfig = new MockFilterConfig();
		this.singleSignOutFilter = new SingleSignOutFilter();
		this.singleSignOutFilter
				.setArtifactParameterName("casSingleSignOutFilter");
		this.singleSignOutFilter.init(singleSignOutFilterConfig);

		MockFilterConfig springSecurityFilterChainConfig = new MockFilterConfig();
		this.delegatingFilterProxy = new DelegatingFilterProxy(
				"springSecurityFilterChain", applicationContext);
		this.delegatingFilterProxy.init(springSecurityFilterChainConfig);

		// HttpSessionEvent httpSessionEvent=new HttpSessionEvent(null);
		this.singleSignOutListener = new SingleSignOutHttpSessionListener();
		this.singleSignOutListener.sessionCreated(null);

		// spring mvc
		MockServletConfig servletConfig = new MockServletConfig();
		this.dispatcherServlet = new DispatcherServlet(applicationContext);
		this.dispatcherServlet.init(servletConfig);

	}

	@Override
	public void initChannel(SocketChannel channel) throws Exception {
		//待解决问题，cas登录后不跳转，可能是证书问题
		ChannelPipeline pipeline = channel.pipeline();
		// Uncomment the following line if you want HTTPS
		File certChainFile = ResourceUtils
				.getFile("classpath:certifications/cas_cert.pem");
		com.dm.common.util.Assert.notNull(certChainFile,
				"Certification file can't be null!");
		File keyFile = ResourceUtils
				.getFile("classpath:certifications/privateKey_pkcs8.pem");
		com.dm.common.util.Assert.notNull(keyFile, "Key file can't be null!");
		SslContext sslCtx = SslContext.newServerContext(certChainFile, keyFile);
		pipeline.addLast("ssl",
				new SslHandler(sslCtx.newEngine(channel.alloc())));

		// ////////////////////////////
		// KeyManagerFactory kmf = null;
		// InputStream in = null;
		// String pkPath="D:/keys/mycas.crt";
		// if (pkPath != null) {
		// KeyStore ks = KeyStore.getInstance("CRT");
		// in = new FileInputStream(pkPath);
		// ks.load(in, "123456".toCharArray());
		// kmf=KeyManagerFactory.getInstance("SunX509");
		// kmf.init(ks, "123456".toCharArray());
		// }
//		SSLEngine engine = SecureChatSslContextFactory.getClientContext()
//				.createSSLEngine();
//		engine.setUseClientMode(false);
//		engine.setNeedClientAuth(true);
		
//		pipeline.addLast("ssl", new SslHandler(engine));
		// //////////////////////////
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
//		pipeline.addLast("ssoHandler", new SsoHandler(this.singleSignOutFilter,this.dispatcherServlet));
		pipeline.addLast("ssfHandler", new SsfHandler(
				this.delegatingFilterProxy,this.dispatcherServlet));
		//为什么添加了springmvc后就不跳转到cas登录页面
		pipeline.addLast("springMvcHandler", new ServletNettyHandler(
				this.dispatcherServlet));

	}

}
