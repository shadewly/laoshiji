package com.aus.security.core;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

public class CasRestClient {
	public static String getTicket(final String server, final String username,
			final String password, final String service) {
		notNull(server, "server must not be null");
		notNull(username, "username must not be null");
		notNull(password, "password must not be null");
		notNull(service, "service must not be null");

		return getServiceTicket(server,
				getTicketGrantingTicket(server, username, password), service);
	}

	/**
	 * 取得ST
	 * 
	 * @param server
	 * @param ticketGrantingTicket
	 * @param service
	 */
	private static String getServiceTicket(final String server,
			final String ticketGrantingTicket, final String service) {
		if (ticketGrantingTicket == null)
			return null;
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost(server + "/"
					+ ticketGrantingTicket);

			List<NameValuePair> fromParams = new ArrayList<NameValuePair>();

			fromParams.add(new BasicNameValuePair("service", service));

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(fromParams,
					"UTF-8");

			httpPost.setEntity(entity);
			response = client.execute(httpPost);

			final HttpEntity responseEntity = response.getEntity();

			switch (response.getStatusLine().getStatusCode()) {
			case 200:
				return responseEntity.toString();

			default:
				warning("Invalid response code ("
						+ response.getStatusLine().getStatusCode()
						+ ") from CAS server!");
				info("Response (1k): "
						+ responseEntity.toString().substring(
								0,
								Math.min(1024, responseEntity.toString()
										.length())));
				break;
			}
		}

		catch (final Exception e) {
			warning(e.getMessage());
		}

		finally {
			try {
				if (null != response) {
					response.close();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return null;

		// final HttpClient client = new HttpClient();
		//
		// final PostMethod post = new PostMethod(server + "/" +
		// ticketGrantingTicket);
		//
		// post.setRequestBody(new NameValuePair[] { new
		// NameValuePair("service", service) });
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
	}

	/**
	 * @param server
	 * @param username
	 * @param password
	 */
	private static String getTicketGrantingTicket(final String server,
			final String username, final String password) {
		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();

			final HttpPost httpPost = new HttpPost(server);

			List<NameValuePair> fromParams = new ArrayList<NameValuePair>();

			fromParams.add(new BasicNameValuePair("username", username));
			fromParams.add(new BasicNameValuePair("password", password));

			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(fromParams,
					"UTF-8");

			httpPost.setEntity(entity);
			response = client.execute(httpPost);

			final String responseEntityStr = response.toString();
			info("TGT=" + response);
			switch (response.getStatusLine().getStatusCode()) {
			case 201: {
				final Matcher matcher = Pattern.compile(
						".*Location: .*/(.*?).*").matcher(responseEntityStr);
//				".*action=\".*/(.*?)\".*"

				if (matcher.matches()){
					for(int i=0;i<matcher.groupCount();i++){
						System.out.println(matcher.group(i));
					}
					
					return matcher.group(1);
				}
					

				warning("Successful ticket granting request, but no ticket found!");
				info("Response (1k): "
						+ responseEntityStr.substring(0,
								Math.min(1024, responseEntityStr.length())));
				break;
			}

			default:
				warning("Invalid response code ("
						+ response.getStatusLine().getStatusCode()
						+ ") from CAS server!");
				info("Response (1k): "
						+ responseEntityStr.substring(0,
								Math.min(1024, responseEntityStr.length())));
				break;
			}
		}

		catch (final IOException e) {
			warning(e.getMessage());
			e.printStackTrace();
		}

		finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			;
		}

		return null;
		// final HttpClient client = new HttpClient();
		//
		// final PostMethod post = new PostMethod(server);
		//
		// post.setRequestBody(new NameValuePair[] {
		// new NameValuePair("username", username),
		// new NameValuePair("password", password) });
		//
		// try {
		// client.executeMethod(post);
		//
		// final String response = post.getResponseBodyAsString();
		// info("TGT=" + response);
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
	}

	public static boolean ticketValidate(String serverValidate,
			String serviceTicket, String service) {
		notNull(serviceTicket, "paramter 'serviceTicket' is not null");
		notNull(service, "paramter 'service' is not null");

		CloseableHttpResponse response = null;
		try {
			CloseableHttpClient client = HttpClients.createDefault();
			final HttpPost httpPost = new HttpPost(serverValidate + "?"
					+ "ticket=" + serviceTicket + "&service="
					+ URLEncoder.encode(service, "UTF-8"));

			response = client.execute(httpPost);

			final String responseEntityStr = response.getEntity().toString();
			info(responseEntityStr);
			switch (response.getStatusLine().getStatusCode()) {
			case 200: {
				info("成功取得用户数据");
				return true;
			}
			default: {

			}
			}

		} catch (Exception e) {
			warning(e.getMessage());
		} finally {
			// 释放资源
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
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
		// info("成功取得用户数据");
		// return true;
		// }
		// default: {
		//
		// }
		// }
		//
		// } catch (Exception e) {
		// warning(e.getMessage());
		// } finally {
		// // 释放资源
		// post.releaseConnection();
		// }
		// return false;

	}

	private static void notNull(final Object object, final String message) {
		if (object == null)
			throw new IllegalArgumentException(message);
	}

	public static void main(final String[] args) throws Exception {
		final String server = "https://145q8w2034.iok.la:8443/cas/v1/tickets";
		final String username = "yxc";
		final String password = "123";
		final String service = "https://www.baidu.com"; // 随意写
		final String proxyValidate = "https://145q8w2034.iok.la:8443/cas/proxyValidate";

		ticketValidate(proxyValidate,
				getTicket(server, username, password, service), service);

	}

	private static void warning(String msg) {
		System.out.println(msg);
	}

	private static void info(String msg) {
		System.out.println(msg);
	}
}
