package com.testmile.setu.actor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.google.gson.Gson;
import com.testmile.setu.actor.core.guiauto.translator.DriverTranslator;
import com.testmile.setu.actor.core.guiauto.translator.GuiAutoTranslator;
import com.testmile.setu.actor.core.guiauto.translator.Response;
import com.testmile.setu.actor.core.guiauto.translator.SikuliTranslator;
import com.testmile.trishanku.tpi.webserver.JsonUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class GuiAutoServlet extends HttpServlet {
	private static Gson gson = new Gson();
	private Map<String, GuiAutoTranslator> guiAutoTranslatorMap = new HashMap<String, GuiAutoTranslator>();

	/**
	 * 
	 */
	private static final long serialVersionUID = 3372756574410648998L;
	
	protected synchronized void registerTranslator(String setuId, GuiAutoTranslator translator) throws Exception {
		this.guiAutoTranslatorMap.put(setuId, translator);
	}
	
	protected synchronized void removeAutomatorHandler(String setuId) throws Exception {
		this.guiAutoTranslatorMap.remove(setuId);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String retContent =  null;
		GuiAutoRequest req = new GuiAutoRequest(request);
		String path = req.getPath();
		GuiAutoTranslator guiAutoTranslator;
		ActorAction action = req.getAction();
		String automatorId = action.getAutomatorId();
		try {
			switch (path){
			case "/automator/launch":
				String automator = action.getAutomatorName().toLowerCase();
				if (automator.equals("sikuli")) {
					guiAutoTranslator = new SikuliTranslator(automatorId);
				} else if (automator.equals("selenium")) {
					guiAutoTranslator = DriverTranslator.seleniumTranslator(automatorId, automator);
				} else if (automator.equals("appium")) {
					guiAutoTranslator = DriverTranslator.appiumTranslator(automatorId, automator);
				} else {
					throw new Exception(String.format("Unrecognized Gui Automator name: %s", action.getAutomatorName()));
				}
				this.registerTranslator(automatorId, guiAutoTranslator);
				guiAutoTranslator.launchAutomator(action.getArgs());
				retContent = Response.createSuccessResponseString();
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
			retContent = Response.createErrorResponseString(e);
		}
		
		response.getWriter().println(retContent);
		response.flushBuffer();	
	}
}