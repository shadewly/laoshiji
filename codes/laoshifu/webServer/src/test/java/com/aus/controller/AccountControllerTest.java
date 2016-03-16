package com.aus.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class AccountControllerTest {

	private static String serverUri = "https://145q8w2034.iok.la:27044/webServer/";

	/**
	 * 00:计划采购入库 insertInStoreDrugTest1:计划申请入库<br>
	 * 描述方法的适用条件-可选<br>
	 */
	@Test
	public void register() {

		try {

			CloseableHttpClient client = HttpClients.createDefault();
			// HttpPost httpPost = new HttpPost(serverUri
			// + "accountController?register");
			HttpPost httpPost = new HttpPost(serverUri+"accountController?register");

			List<NameValuePair> fromParams = new ArrayList<NameValuePair>();

			fromParams.add(new BasicNameValuePair("accountNo", "yxc"));
			fromParams.add(new BasicNameValuePair("password", "123"));

			 UrlEncodedFormEntity entity = new
			 UrlEncodedFormEntity(fromParams,"UTF-8");
			
			 httpPost.setEntity(entity);

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

	public static void main(String args[]) {
		sendUrl(null, null, null);
		// 创建HttpClientBuilder
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		// HttpClient
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

		HttpPost httpPost = new HttpPost(
				"https://145q8w2034.iok.la:8445/webServer/accountController?register");
		// httpPost.setConfig(DEFAULT);
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		formparams.add(new BasicNameValuePair("searchText", "英语"));

		UrlEncodedFormEntity entity;
		try {
			entity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httpPost.setEntity(entity);

			HttpResponse httpResponse;
			// post请求
			httpResponse = closeableHttpClient.execute(httpPost);

			// getEntity()
			HttpEntity httpEntity = httpResponse.getEntity();
			if (httpEntity != null) {
				// 打印响应内容
				System.out.println("response:"
						+ EntityUtils.toString(httpEntity, "UTF-8"));
			}
			// 释放资源
			closeableHttpClient.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String sendUrl(String urlStr, String commond, String params) {

		OutputStream out = null;
		BufferedReader in = null;
		StringBuffer result = new StringBuffer();
		URLConnection conn = null;
		try {
			URL url = new URL(
					"http://145q8w2034.iok.la:8080/webServer/accountController?register");
			// 打开和URL之间的连接
			conn = url.openConnection();
			HttpURLConnection httpUrlConnection = (HttpURLConnection) conn;

			// 发送POST请求必须设置如下两行
			// 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
			// http正文内，因此需要设为true, 默认情况下是false;
			httpUrlConnection.setDoOutput(true);

			// 设置是否从httpUrlConnection读入，默认情况下是true;
			httpUrlConnection.setDoInput(true);

			// Post 请求不能使用缓存
			httpUrlConnection.setUseCaches(false);

			// 设定请求的方法为"POST"，默认是GET
			httpUrlConnection.setRequestMethod("GET");

			// 设置http请求主机地址超时
			// httpUrlConnection.setConnectTimeout(1500);

		

			//
			httpUrlConnection.setRequestProperty("Pragma:", "no-cache");
			httpUrlConnection.setRequestProperty("Cache-Control", "no-cache");
			httpUrlConnection.setRequestProperty("Content-Type",
					"text/html;charset=UTF-8");

			out = httpUrlConnection.getOutputStream();
//			if (!params.equalsIgnoreCase("")) {
//				out.write(params.toString().getBytes("utf-8"));
//			}
			out.flush();
			out.close();
			Charset.defaultCharset();
			// 定义BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(
					httpUrlConnection.getInputStream(), "UTF-8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result.append(line);
			}
			// System.out.println("成功连接SDA服务器！");
		} catch (Exception e) {
			 e.printStackTrace();
			System.out.println("无法连接SDA服务器！");
		} finally {
			try {
				if (out != null) {
					out.close();
					out = null;
				}
				if (in != null) {
					in.close();
					in = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result.toString().trim();
	}

}
