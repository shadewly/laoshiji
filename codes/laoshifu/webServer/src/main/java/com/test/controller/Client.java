package com.test.controller;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

public class Client {

	// public static void main(String[] args) throws Exception {
	// HttpClient client = new DefaultHttpClient();
	// String path = "https://localhost:8443/cas/v1/tickets";
	// HttpPost post = new HttpPost(path);
	// // Vendor v = new Vendor();
	// // v.setName("��������");
	// // v.setDescription("���ݴ���");
	// // v.setCreateDate(new Date());
	// // v.setId(20);
	// // String content = JSONBinder.binder(Vendor.class).toJSON(v);
	// StringEntity entity = new StringEntity("username=yxc&password=123");
	// entity.setContentEncoding("UTF-8");
	// entity.setContentType("application/x-www-form-urlencoded");
	// post.setEntity(entity);
	// HttpResponse response = client.execute(post);
	// System.out.println("��Ӧ״̬�룺" + response.getStatusLine().getStatusCode());
	// InputStream is = response.getEntity().getContent();
	// // String text = StreamUtil.readInputStream(is);
	// // System.out.println("����������Ӧ�����ݣ�" + text);
	// }

	// ///////////////////////////////////////////////////////
	// public static String getTicket(final String server, final String
	// username,
	// final String password, final String service) {
	// notNull(server, "server must not be null");
	// notNull(username, "username must not be null");
	// notNull(password, "password must not be null");
	// notNull(service, "service must not be null");
	//
	// return getServiceTicket(server,
	// getTicketGrantingTicket(server, username, password), service);
	// }
	//
	// /**
	// * ȡ��ST
	// *
	// * @param server
	// * @param ticketGrantingTicket
	// * @param service
	// */
	// private static String getServiceTicket(final String server,
	// final String ticketGrantingTicket, final String service) {
	// if (ticketGrantingTicket == null)
	// return null;
	//
	// final HttpClient client = new HttpClient();
	//
	// final PostMethod post = new PostMethod(server + "/"
	// + ticketGrantingTicket);
	//
	// post.setRequestBody(new NameValuePair[] { new NameValuePair("service",
	// service) });
	//
	// try {
	// client.executeMethod(post);
	//
	// final String response = post.getResponseBodyAsString();
	//
	// switch (post.getStatusCode()) {
	// case 200:
	// return response;
	//
	// default:
	// warning("Invalid response code (" + post.getStatusCode()
	// + ") from CAS server!");
	// info("Response (1k): "
	// + response.substring(0,
	// Math.min(1024, response.length())));
	// break;
	// }
	// }
	//
	// catch (final IOException e) {
	// warning(e.getMessage());
	// }
	//
	// finally {
	// post.releaseConnection();
	// }
	//
	// return null;
	// }

	/**
	 * @param server
	 * @param username
	 * @param password
	 */
	private static String getTicketGrantingTicket(final String server,
			final String username, final String password) {
		final HttpClient client = new DefaultHttpClient();
		String server1 = "https://localhost:8443/cas/v1/tickets";
		final HttpPost post = new HttpPost(server1);

		try {
			StringEntity entity = new StringEntity("username=yxc&password=123");
			post.setEntity(entity);
			;
			final HttpResponse response = client.execute(post);

			final int bodystr = response.getStatusLine().getStatusCode();
			info("TGT=" + response);
			// switch (post.getStatusCode()) {
			// case 201: {
			// final Matcher matcher = Pattern.compile(
			// ".*action=\".*/(.*?)\".*").matcher(response);
			//
			// if (matcher.matches())
			// return matcher.group(1);
			//
			// warning("Successful ticket granting request, but no ticket found!");
			// info("Response (1k): "
			// + response.substring(0,
			// Math.min(1024, response.length())));
			// break;
			// }
			//
			// default:
			// warning("Invalid response code (" + post.getStatusCode()
			// + ") from CAS server!");
			// info("Response (1k): "
			// + response.substring(0,
			// Math.min(1024, response.length())));
			// break;
			// }
		}

		catch (final Exception e) {
			warning(e.getMessage());
		}

		finally {
			post.releaseConnection();
		}

		return null;
	}

	// private static void ticketValidate(String serverValidate,
	// String serviceTicket, String service) {
	// notNull(serviceTicket, "paramter 'serviceTicket' is not null");
	// notNull(service, "paramter 'service' is not null");
	//
	// final HttpClient client = new HttpClient();
	// GetMethod post = null;
	//
	// try {
	// post = new GetMethod(serverValidate + "?" + "ticket="
	// + serviceTicket + "&service="
	// + URLEncoder.encode(service, "UTF-8"));
	// client.executeMethod(post);
	//
	// final String response = post.getResponseBodyAsString();
	// info(response);
	// switch (post.getStatusCode()) {
	// case 200: {
	// info("�ɹ�ȡ���û�����");
	// }
	// default: {
	//
	// }
	// }
	//
	// } catch (Exception e) {
	// warning(e.getMessage());
	// } finally {
	// // �ͷ���Դ
	// post.releaseConnection();
	// }
	//
	// }

	// private static void notNull(final Object object, final String message) {
	// if (object == null)
	// throw new IllegalArgumentException(message);
	// }

	public static void main(final String[] args) throws Exception {
		final String server = "http://localhost:8080/CASServer/v1/tickets";
		final String username = "username";
		final String password = "username";
		final String service = "http://localhost:8080/service"; // ����д
		// final String proxyValidate =
		// "http://localhost:8080/<span style='font-family: Arial, Helvetica, sans-serif;'>CASServer</span>/proxyValidate";
		//
		// ticketValidate(proxyValidate,
		// getTicket(server, username, password, service), service);

		getTicketGrantingTicket(server, username, password);
	}

	private static void warning(String msg) {
		System.out.println(msg);
	}

	private static void info(String msg) {
		System.out.println(msg);
	}
}
