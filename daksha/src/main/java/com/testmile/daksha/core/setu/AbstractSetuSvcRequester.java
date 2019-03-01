package com.testmile.daksha.core.setu;

import com.testmile.trishanku.tpi.httpclient.BasicRestClient;
import com.testmile.trishanku.tpi.httpclient.SetuHttpException;

public abstract class AbstractSetuSvcRequester implements SetuSvcRequester {
	private String setuUrl = "http://localhost:9000";
	protected BasicRestClient restClient;

	public AbstractSetuSvcRequester(String baseUri) {
		this.restClient = new BasicRestClient(setuUrl + baseUri);
	}

	/* (non-Javadoc)
	 * @see com.testmile.daksha.core.setu.SetuSvRequester#post(java.lang.String, com.testmile.daksha.core.setu.SetuRequest)
	 */
	@Override
	public Response post(String uri, SetuRequest action) throws Exception {
		System.out.println(uri);
		System.out.println(action.asJsonString());
		try {
			String response = this.restClient.post(uri, action.asJsonString());
			System.out.println(response);
			return Response.fromJsonStr(response);
		} catch (SetuHttpException e) {
			System.out.println(e.getResponse());
			throw e;
		} catch (Exception f) {
			throw f;
		}
	}

}