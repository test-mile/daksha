package com.testmile.setu.actor;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.testmile.trishanku.tpi.webserver.JsonUtils;

public class GuiAutoRequest{
	private String path;
	private ActorAction action;
	private String jsonStr;
	
	public GuiAutoRequest(HttpServletRequest request) throws IOException {
		jsonStr = JsonUtils.asJsonString(request.getInputStream());
		System.out.println("JsonInput:" + jsonStr);	
		path = request.getPathInfo();
		action = ActorAction.fromJsonStr(jsonStr);	
	}
	
	public ActorAction getAction() {
		return this.action;
	}
	
	public String getPath() {
		return path;
	}
	
	public String getJsonBody() {
		return jsonStr;
	}
}


