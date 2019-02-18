package setuexp;

import com.testmile.trishanku.tpi.httpclient.BasicRestClient;
import com.testmile.trishanku.tpi.httpclient.SetuHttpException;

public class SetuGuiAutoSvcClient {
	private BasicRestClient restClient;
	
	public SetuGuiAutoSvcClient() {
		this.restClient = new BasicRestClient("http://localhost:9000/guiautomator");
	}

	public Response post(String uri, GuiAction action) throws Exception {
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
