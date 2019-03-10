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

package com.testmile.setu.actor.guiauto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.google.gson.Gson;
import com.testmile.setu.actor.guiauto.adapter.GuiAutoAdapter;
import com.testmile.setu.actor.guiauto.adapter.driver.DriverAdapter;
import com.testmile.setu.actor.guiauto.adapter.sikuli.SikuliAdapter;
import com.testmile.trishanku.tpi.setu.actor.ActorAction;
import com.testmile.trishanku.tpi.setu.actor.ServerResponse;
import com.testmile.trishanku.tpi.setu.actor.SetuRequest;

public class GuiAutoServlet extends HttpServlet {
	private static Gson gson = new Gson();
	private Map<String, GuiAutoAdapter> guiAutoTranslatorMap = new HashMap<String, GuiAutoAdapter>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 3372756574410648998L;
	
	protected synchronized void registerTranslator(String setuId, GuiAutoAdapter translator) throws Exception {
		this.guiAutoTranslatorMap.put(setuId, translator);
	}
	
	protected synchronized void removeAutomatorHandler(String setuId) throws Exception {
		this.guiAutoTranslatorMap.remove(setuId);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String retContent =  null;
		SetuRequest req = new SetuRequest(request);
		String path = req.getPath();
		GuiAutoAdapter guiAutoTranslator;
		ActorAction action = req.getAction();
		String automatorId = action.getAutomatorId();
		try {
			switch (path){
			case "/automator/launch":
				String automator = action.getAutomatorName().toLowerCase();
				if (automator.equals("sikuli")) {
					guiAutoTranslator = new SikuliAdapter(automatorId);
				} else if (automator.equals("selenium")) {
					guiAutoTranslator = DriverAdapter.Selenium(automatorId, automator);
				} else if (automator.equals("appium")) {
					guiAutoTranslator = DriverAdapter.Appium(automatorId, automator);
				} else {
					throw new Exception(String.format("Unrecognized Gui Automator name: %s", action.getAutomatorName()));
				}
				this.registerTranslator(automatorId, guiAutoTranslator);
				guiAutoTranslator.launchAutomator(action.getArgs());
				retContent = ServerResponse.createSuccessResponseString();
				break;
			case "/automator/action":
				guiAutoTranslator = guiAutoTranslatorMap.get(action.getAutomatorId());
				retContent = guiAutoTranslator.takeAction(action);
				if (action.getActionString().toLowerCase().equals("quit")) {
					removeAutomatorHandler(action.getAutomatorId());
				}
				break;
			case "/element/action":
				guiAutoTranslator = guiAutoTranslatorMap.get(action.getAutomatorId());
				retContent = guiAutoTranslator.takeElementAction(action.getElementId(), action);
				break;
			default:
				throw new Exception(String.format("Requested link: %s is invalid.", request.getRequestURI()));
			}
		} catch (Exception e) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR_500);
			retContent = ServerResponse.createErrorResponseString(e);
		}
		
		response.getWriter().println(retContent);
		response.flushBuffer();	
	}
}