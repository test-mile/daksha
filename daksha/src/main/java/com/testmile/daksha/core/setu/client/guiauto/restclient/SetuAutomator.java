package com.testmile.daksha.core.setu.client.guiauto.restclient;

import com.google.gson.Gson;
import com.testmile.daksha.core.setu.client.guiauto.proxy.page.GuiMetaData;
import com.testmile.trishanku.tpi.httpclient.BasicRestClient;

public class SetuAutomator {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BasicRestClient client = new BasicRestClient("http://localhost:9898");
		System.out.println(client.get("/items"));
		GuiMetaData md = new GuiMetaData();
		md.setAutomatorId("dfgkjfdhgkjhdfgkdhfgkjhdgkjhdfgg");
		Gson gson = new Gson();
		String out = gson.toJson(md);
		System.out.println(out);
	}

}
