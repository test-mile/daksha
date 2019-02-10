package com.testmile.trishanku.tpi.httpclient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

public class BasicRestClient {
	private String baseUrl = null;
	private HttpHost proxy = null;
	private CloseableHttpClient httpclient = null;
	private ResponseHandler<String> responseHandler;
	
	public BasicRestClient(String baseUrl){
		httpclient = HttpClients.createDefault();
		this.baseUrl = baseUrl;	
        // Create a custom response handler
        responseHandler = new ResponseHandler<String>() {

            @Override
            public String handleResponse(
                    final HttpResponse response) throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            }

        };
	}
	
	public String get(String uri) throws Exception {
        try {
            HttpGet httpget = new HttpGet(this.baseUrl +  uri);
            System.out.println("Executing request " + httpget.getRequestLine());
            String responseBody = httpclient.execute(httpget, responseHandler);
            System.out.println("----------------------------------------");
            return responseBody;
        } finally {
            httpclient.close();
        }
	}
	
	public String post(String uri, String content, String contentEncoding){

		String strResponse = null;
		try {
				HttpPost httppost = new HttpPost(this.baseUrl +  uri);
		
				StringEntity se = new StringEntity(content);
				se.setContentType("application/json");
				se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "UTF-8"));
				httppost.setEntity(se);
				org.apache.http.HttpResponse response = httpclient.execute(httppost);
				strResponse = EntityUtils.toString(response.getEntity());
				
	            System.out.println("Executing request " + httppost.getRequestLine());
	            String responseBody = httpclient.execute(httppost, responseHandler);
	            System.out.println("----------------------------------------");
	            return responseBody;
	
		} catch (ClientProtocolException e) {
				e.printStackTrace();
		} catch (IOException e) {
				e.printStackTrace();
		}
		return strResponse;
	}
}

