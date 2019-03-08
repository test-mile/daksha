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

package com.testmile.setu.actor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.Gson;
import com.testmile.setu.actor.core.guiauto.translator.DriverTranslator;
import com.testmile.setu.actor.core.guiauto.translator.SikuliTranslator;
import com.testmile.setu.actor.core.guiauto.translator.GuiAutoTranslator;
import com.testmile.setu.actor.core.guiauto.translator.Response;
import com.testmile.trishanku.tpi.value.AnyRefValue;
import com.testmile.trishanku.tpi.value.Value;
import com.testmile.trishanku.tpi.webserver.AbstractJettyServer;
import com.testmile.trishanku.tpi.webserver.JsonUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SetuActor 
{
	public static void main(String[] args) throws Exception{
		String contextPath = "/setu/connect";
		SetuWebService ws = null;
		if (args.length == 0){
			ws = new SetuWebService(9898, contextPath);
		} else {
			ws = new SetuWebService(Integer.parseInt(args[0].trim()), contextPath);
		}
		
		ws.launch();
	}
}

class SetuWebService extends AbstractJettyServer{

	public SetuWebService(int port, String rootContextPath) throws Exception {
		super(port,rootContextPath);
	}

	@Override
	protected List<ServletContextHandler> getServiceHandlers() throws Exception {
		List<ServletContextHandler> handlers = new ArrayList<ServletContextHandler>();
		ServletContextHandler guiauto = new ServletContextHandler();
		guiauto.setContextPath("/setuactor/guiauto");
		guiauto.addServlet(GuiAutoServlet.class, "/*");
		handlers.add(guiauto);
		return handlers;
	}
}
