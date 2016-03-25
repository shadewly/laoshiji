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

import org.springframework.util.ResourceUtils;

/**
 *
 */
public class BaseChannelInitializer extends ChannelInitializer<SocketChannel> {


	@Override
	public void initChannel(SocketChannel channel) throws Exception {
		//待解决问题，cas登录后不跳转，可能是证书问题
		ChannelPipeline pipeline = channel.pipeline();
		// Uncomment the following line if you want HTTPS
		File certChainFile = ResourceUtils
				.getFile("classpath:certifications/cas_cert.pem");
		com.wb.common.util.Assert.notNull(certChainFile,
				"Certification file can't be null!");
		File keyFile = ResourceUtils
				.getFile("classpath:certifications/privateKey_pkcs8.pem");
		com.wb.common.util.Assert.notNull(keyFile, "Key file can't be null!");
		SslContext sslCtx = SslContext.newServerContext(certChainFile, keyFile);
		pipeline.addLast("ssl",
				new SslHandler(sslCtx.newEngine(channel.alloc())));

		// //////////////////////////
		pipeline.addLast("decoder", new HttpRequestDecoder());
		pipeline.addLast("aggregator", new HttpObjectAggregator(65536));
		pipeline.addLast("encoder", new HttpResponseEncoder());
		pipeline.addLast("chunkedWriter", new ChunkedWriteHandler());
//		pipeline.addLast("ssoHandler", new SsoHandler(this.singleSignOutFilter,this.dispatcherServlet));
		pipeline.addLast("dispatchHandler", new DispatchHandler());


	}

}
