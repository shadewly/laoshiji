package com.netty.server;

import io.netty.handler.ssl.util.SimpleTrustManagerFactory;

import java.io.FileInputStream;
import java.security.KeyStore;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecureChatSslContextFactory {
	private static Logger logger = LoggerFactory
			.getLogger(SecureChatSslContextFactory.class);
	private static final String PROTOCOL = "TLS";
	private static final SSLContext SERVER_CONTEXT;
	private static final SSLContext CLIENT_CONTEXT;
	static {
		SSLContext serverContext = null;
		SSLContext clientContext = null;
		// get keystore and trustore locations and passwords
		String keyStoreLocation = System.getProperty("javax.net.ssl.keyStore");
		String keyStorePassword = System
				.getProperty("javax.net.ssl.keyStorePassword");
		String trustStoreLocation = System
				.getProperty("javax.net.ssl.trustStore");
		String trustStorePassword = System
				.getProperty("javax.net.ssl.trustStorePassword");
		try {
			KeyStore ks = KeyStore.getInstance("JKS");
			String pkPath = "D:/keys/mycas.keystore";

			ks.load(new FileInputStream(pkPath), "123456".toCharArray());
			// Set up key manager factory to use our key store
			KeyManagerFactory kmf = KeyManagerFactory
					.getInstance(KeyManagerFactory.getDefaultAlgorithm());
			kmf.init(ks, "123456".toCharArray());
			// truststore
			KeyStore ts = KeyStore.getInstance("JKS");
			ts.load(new FileInputStream(pkPath), "123456".toCharArray());
			// set up trust manager factory to use our trust store
			TrustManagerFactory tmf = TrustManagerFactory
					.getInstance(TrustManagerFactory.getDefaultAlgorithm());
			tmf.init(ts);
			// Initialize the SSLContext to work with our key managers.
			serverContext = SSLContext.getInstance(PROTOCOL);
			serverContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(),
					null);

			clientContext = SSLContext.getInstance(PROTOCOL);
			clientContext.init(null, tmf.getTrustManagers(), null);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error("Failed to initialize the client-side SSLContext",
					e);
		}
		SERVER_CONTEXT = serverContext;
		CLIENT_CONTEXT = clientContext;
	}

	public static SSLContext getServerContext() {
		return SERVER_CONTEXT;
	}

	public static SSLContext getClientContext() {
		return CLIENT_CONTEXT;
	}

	private SecureChatSslContextFactory() {
		// Unused
	}
}
