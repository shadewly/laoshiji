package com.lsf.util;

import android.accounts.NetworkErrorException;
import android.util.Log;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;


/**
 * Created by ZY on 2016/3/6.
 */
public class HttpUtils {

  //  static PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
   // public static CloseableHttpClient httpClient = HttpClients.custom()
  //          .setConnectionManager(cm)
    //        .build();




    public static void sendGetRequest(final String urlAddress, final HttpCallbackListener listener) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection conn = null;
                try{
                    URL url = new URL(urlAddress);
                    conn = (HttpURLConnection)url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setReadTimeout(5000);
                    conn.setConnectTimeout(10000);
                    InputStream in = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while(( line = reader.readLine())!=null){
                        response.append(line);
                    }

                    if(listener != null){
                        listener.onFinish(response.toString());
                    }
                }catch(Exception e){
                    if(listener!=null){
                        listener.onError(e);
                    }
                }finally {
                    if(conn!=null){
                        conn.disconnect();
                    }
                }

            }
        }).start();
                /*HttpPost request = new HttpPost(url);
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"UTF-8");
                //request.setEntity(formEntity);
                Log.d("", "executing request: " + request.getURI());

                ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                    @Override
                    public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
                        int status = response.getStatusLine().getStatusCode();
                        if (status > 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                    }
                };
                responseBody =  httpClient.execute(request,responseHandler);
            }finally {
                httpClient.close();;
            }
        }*/

    }

    public static void sendPostRequest(final String urlAddress, final String content, final HttpCallbackListener listener){

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpsURLConnection conn = null;
                try{
                    URL url = new URL(urlAddress);
                    trustAllHosts();
                    conn = (HttpsURLConnection)url.openConnection();
                    conn.setHostnameVerifier(DO_NOT_VERIFY);
                    conn.setRequestMethod("POST");
                    conn.setReadTimeout(60000);
                    conn.setConnectTimeout(60000);
                    conn.setDoOutput(true);
                    OutputStream out = conn.getOutputStream();
                    out.write(content.getBytes());
                    out.flush();
                    out.close();

                    String response = getStringFromInputStream(conn.getInputStream());

                    if(listener != null){
                        listener.onFinish(response.toString());
                    }

                }catch(Exception e){
                    if(listener != null){
                        listener.onError(e);
                    }
                }
            }
        }).start();
    }


    private static String getStringFromInputStream(InputStream in) throws IOException{
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int len = -1;
        while((len=in.read(buffer)) != -1){
            os.write(buffer,0,len);
        }
        in.close();
        String result = os.toString();
        os.close();
        return result;

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while((line = reader.readLine())!=null){
            sb.append(line);
        }
        sb.toString();*/
    }

    public static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        // Android use X509 cert
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };
}

