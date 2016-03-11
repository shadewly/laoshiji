package com.aus.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

public class AccountControllerTest {

	private static String serverUri = "https://145q8w2034.iok.la:8445/webServer/";

	/**
	 * 00:计划采购入库 insertInStoreDrugTest1:计划申请入库<br>
	 * 描述方法的适用条件-可选<br>
	 */
	@Test
	public void register() {

		try {

			CloseableHttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost(serverUri
					+ "accountController?register");

			List<NameValuePair> fromParams = new ArrayList<NameValuePair>();

			fromParams.add(new BasicNameValuePair("accountNo", "yxc1"));
			fromParams.add(new BasicNameValuePair("password", "123"));

			// UrlEncodedFormEntity entity = new
			// UrlEncodedFormEntity(fromParams,"UTF-8");
			//
			// httpPost.setEntity(entity);

			HttpResponse response = client.execute(httpPost);

			System.out.println(response.getStatusLine().getStatusCode());
			client.close();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	

}
