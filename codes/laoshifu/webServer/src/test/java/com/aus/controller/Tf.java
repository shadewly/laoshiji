package com.aus.controller;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;

public class Tf {
	@Test
	public void aa() {
		final HttpClient client = new HttpClient();

		final PostMethod post = new PostMethod(
				"https://145q8w2034.iok.la:8445/webServer/accountController?register");

		// post.setRequestBody(new NameValuePair[] { new
		// NameValuePair("username", username),
		// new NameValuePair("password", password) });

		try {
			client.executeMethod(post);
			final String response = post.getResponseBodyAsString();
		} catch (HttpException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
