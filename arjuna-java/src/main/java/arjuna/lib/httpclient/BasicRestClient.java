package arjuna.lib.httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

public class BasicRestClient {
	private String baseUrl = null;
	private HttpHost proxy = null;
	private CloseableHttpClient httpclient = null;
	private ResponseHandler<String> responseHandler;
	
	public BasicRestClient(String baseUrl) {
		httpclient = HttpClients.createDefault();
		this.baseUrl = baseUrl;	
	}
	
	private String handleResponse(HttpResponse response) throws Exception{
        int status = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        if (status >= 200 && status < 300) {
            return entity != null ? EntityUtils.toString(entity) : null;		
        } else {
        	if (entity == null) {
        		throw new SetuHttpException("Setu returned null response object.", status, EntityUtils.toString(entity));
        	} else {
        		throw new SetuHttpException("Setu returned an error response.", status, EntityUtils.toString(entity));
        	}
        }
	}
	
	public String get(String uri) throws Exception {
        HttpGet httpget = new HttpGet(this.baseUrl +  uri);
        System.out.println("Executing request " + httpget.getRequestLine());
        String responseBody = handleResponse(httpclient.execute(httpget));
        System.out.println("----------------------------------------");
        return responseBody;
	}
	
	public String post(String uri, String content) throws Exception{
		HttpPost httppost = new HttpPost(this.baseUrl +  uri);

		StringEntity se = new StringEntity(content);
		se.setContentType("application/json");
		se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "UTF-8"));
		httppost.setEntity(se);
        System.out.println("Executing request " + httppost.getRequestLine());
		String response = handleResponse(httpclient.execute(httppost));
		System.out.println(response);
        System.out.println("----------------------------------------");
        return response;
	}
}

