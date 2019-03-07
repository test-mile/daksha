/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.testmile.setu.actor.core.websvc.guiauto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;
import com.testmile.setu.actor.core.websvc.guiauto.helpers.GuiAutomatorHandler;
import com.testmile.setu.actor.core.websvc.guiauto.helpers.Response;
import com.testmile.setu.requester.connector.SetuResponse;
import com.testmile.trishanku.tpi.value.AnyRefValue;
import com.testmile.trishanku.tpi.value.Value;
import com.testmile.trishanku.tpi.webserver.JsonUtils;

public class GuiAutoSvc extends HttpServlet {
	private static Gson gson = new Gson();
	private Map<String, GuiAutomatorHandler> guiAutoHandlerMap = new HashMap<String, GuiAutomatorHandler>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 3372756574410648998L;
	
	protected synchronized GuiAutomatorHandler createAutomatorHandler(String setuId) throws Exception {
		GuiAutomatorHandler handler = new GuiAutomatorHandler(setuId); 
		this.guiAutoHandlerMap.put(setuId, handler);
		return handler;
	}
	
	protected synchronized void removeAutomatorHandler(String setuId) throws Exception {
		this.guiAutoHandlerMap.remove(setuId);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String retContent =  null;
		GuiAutoRequest req = new GuiAutoRequest(request);
		String path = req.getPath();
		GuiAutomatorHandler guiAutoHandler;
		try {
			switch (path){
			case "/guiautomator":
				System.out.println(req.getAutomatorId());
				if (!guiAutoHandlerMap.containsKey(req.getAutomatorId())) {
					guiAutoHandler = createAutomatorHandler(req.getAutomatorId());
				} else {
					guiAutoHandler = guiAutoHandlerMap.get(req.getAutomatorId());
				}
				retContent = guiAutoHandler.takeAction(req.getAction(), req.getArgs());
				if (req.getAction().toLowerCase().equals("quit")) {
					removeAutomatorHandler(req.getAutomatorId());
				}
				break;
			case "/guielement":
				guiAutoHandler = guiAutoHandlerMap.get(req.getAutomatorId());
				retContent = guiAutoHandler.takeElementAction(req.getElementId(), req.getAction(), req.getArgs());
				break;
			default:
				throw new Exception(String.format("Requested link: %s is invalid.", request.getRequestURI()));
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			retContent = Response.createErrorResponseString(e);
		}
		
		response.getWriter().println(retContent);
		response.flushBuffer();	
	}	
}

class ActorRequest {
	private static final Gson gson = new Gson();
	private String action;
	private Map<String,Object> args = null;
	
	public String getAction(){
		System.out.println(action);
		return this.action.trim().toUpperCase();
	}
	
	public Map<String,Object> getArgs(){
		return this.args;
	}
	
	public static ActorRequest fromJsonStr(String json) {
		return gson.fromJson(json, ActorRequest.class);
	}
}


class GuiAutoRequest{
	private String path;
	private ActorRequest req;
	private String jsonStr;
	
	public GuiAutoRequest(HttpServletRequest request) throws IOException {
		jsonStr = JsonUtils.asJsonString(request.getInputStream());
		System.out.println("JsonInput:" + jsonStr);	
		path = request.getPathInfo();
		req = ActorRequest.fromJsonStr(jsonStr);	
	}
	
	public String getElementId() {
		return (String) this.req.getArgs().get("elementSetuId");
	}

	public String getAutomatorId() {
		return (String) this.req.getArgs().get("automatorSetuId");
	}
	
	public String getPath() {
		return path;
	}

	public String getAction() {
		return req.getAction();
	}

	public String getJsonBody() {
		return jsonStr;
	}

	public Map<String,Object> getArgs() {
		return req.getArgs();
	}	
}
