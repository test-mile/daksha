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

package com.testmile.setu.agent.core.websvc.guiauto.helpers;

import java.util.Map;
import java.util.Set;

import com.testmile.setu.agent.guiauto.tpi.automator.GuiAutomator;
import com.testmile.setu.agent.guiauto.tpi.builder.GuiAutomatorFactory;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public class GuiAutomatorHandler {
	private GuiAutomator automator = null;
	private String id = null;
	private GuiElementHandler elementHandler = new GuiElementHandler();

	public GuiAutomatorHandler(String jsonStr, String uuid) throws Exception {
		id = uuid;
		automator = GuiAutomatorFactory.createAutomator(jsonStr);
	}
	
	public String getSetuId() {
		return this.id;
	}

	private GuiElementHandler getElementHandler() {
		return this.elementHandler;
	}
	
	public void quit() throws Exception {
		this.automator.quit();
	}

	public String takeAction(String jsonStr) throws Exception {
		AutomatorAction action = new AutomatorAction(jsonStr);
		String retContent = null;
		switch(action.getActionType()) {
		case GO_TO_URL:
			System.out.println(action.getArgs().get("url").asString());
			this.automator.getBrowserHandler().goTo(action.getArgs().get("url").asString());
			retContent = Response.createSuccessResponseString();
			break;
		case FIND_MULTIELEMENT:
			GuiMultiElement mElement = this.automator.getElementFinder().findAll(
					action.getArgs().get("byType").asString(),
					action.getArgs().get("byValue").asString()
			);
			getElementHandler().registerMultiElement(action.getArgs().get("uuid").asString(), mElement);
			retContent = Response.createSuccessResponseString("instanceCount", mElement.getInstanceCount());
			break;
		case FIND_ELEMENT:
			GuiElement foundElement = this.automator.getElementFinder().find(
					action.getArgs().get("byType").asString(),
					action.getArgs().get("byValue").asString()
			);
			String setu_id = action.getArgs().get("uuid").asString();
			this.getElementHandler().registerElement(setu_id,  foundElement);
			retContent = Response.createSuccessResponseString();
			break;
		case JUMP_TO_FRAME:
			boolean isInstanceAction = action.getArgs().get("isInstanceAction").asBoolean();
			GuiElement fElement;
			if (isInstanceAction) {
				GuiMultiElement mfElement = this.getElementHandler().getMultiElementForSetuId(action.getArgs().get("byValue").asString());
				fElement = mfElement.getInstanceAtIndex(action.getArgs().get("instanceIndex").asInt());
			} else {
				fElement = this.getElementHandler().getElementForSetuId(action.getArgs().get("byValue").asString());
			}
			this.automator.getFrameHandler().jumpToFrame(fElement);
			retContent = Response.createSuccessResponseString();
			break;
		case JUMP_TO_PARENT_FRAME:
			this.automator.getFrameHandler().jumpToParentFrame();
			retContent = Response.createSuccessResponseString();
			break;
		case JUMP_TO_HTML_ROOT:
			this.automator.getFrameHandler().jumpToHtmlRoot();
			retContent = Response.createSuccessResponseString();
			break;
		case CLOSE_CURRENT_WINDOW:
			this.automator.getWindowHandler().closeCurrentWindow();
			retContent = Response.createSuccessResponseString();
			break;
		case GET_ALL_WINDOW_HANDLES:
			Set<String> handles = this.automator.getWindowHandler().getAllWindowHandles();
			retContent = Response.createSuccessResponseString("handles", handles);
			break;
		case GET_WINDOW_TITLE:
			String handle = this.automator.getWindowHandler().getTitle();
			retContent = Response.createSuccessResponseString("title", handle);
			break;
		case GET_CURRENT_WINDOW_HANDLE:
			String title = this.automator.getWindowHandler().getCurrentWindowHandle();
			retContent = Response.createSuccessResponseString("handle", title);
			break;
		case GET_CURRENT_WINDOW_SIZE:
			Map<String,Integer> size = this.automator.getWindowHandler().getCurrentWindowSize();
			retContent = Response.createSuccessResponseString("size", size);
			break;
		case MAXIMIZE_WINDOW:
			this.automator.getWindowHandler().maximizeWindow();
			retContent = Response.createSuccessResponseString();
			break;
		case SET_WINDOW_SIZE:
			this.automator.getWindowHandler().setWindowSize(
					action.getArgs().get("width").asInt(), action.getArgs().get("height").asInt()
			);
			retContent = Response.createSuccessResponseString();
			break;
		case SWITCH_TO_WINDOW:
			this.automator.getWindowHandler().switchToWindow(action.getArgs().get("handle").asString());
			retContent = Response.createSuccessResponseString();
			break;
		case EXECUTE_JAVASCRIPT:
			this.automator.getJsExecutor().executeScript(action.getArgs().get("script").asString());
			retContent = Response.createSuccessResponseString();
			break;
		case IS_ALERT_PRESENT:
			boolean alertPresent = this.automator.getAlertHandler().isAlertPresent();
			retContent = Response.createSuccessResponseString("checkResult", alertPresent);
			break;			
		case CONFIRM_ALERT:
			this.automator.getAlertHandler().confirmAlert();
			retContent = Response.createSuccessResponseString();
			break;
		case DISMISS_ALERT:
			this.automator.getAlertHandler().dismissAlert();
			retContent = Response.createSuccessResponseString();
			break;
		case SEND_TEXT_TO_ALERT:
			this.automator.getAlertHandler().sendTextToAlert(action.getArgs().get("text").asString());
			retContent = Response.createSuccessResponseString();
			break;
		case GET_TEXT_FROM_ALERT:
			String alertText = this.automator.getAlertHandler().getTextFromAlert();
			retContent = Response.createSuccessResponseString("text", alertText);
			break;
		case GET_CURRENT_VIEW_CONTEXT:
			String viewContext = this.automator.getViewHandler().getCurrentViewContext();
			retContent = Response.createSuccessResponseString("viewContext", viewContext);
			break;
		case GET_ALL_VIEW_CONTEXTS:
			Set<String> viewContexts = this.automator.getViewHandler().getAllViewContexts();
			retContent = Response.createSuccessResponseString("viewContexts", viewContexts);		
			break;
		case SWITCH_TO_VIEW_CONTEXT:
			this.automator.getViewHandler().switchToViewContext(action.getArgs().get("viewContext").asString());
			retContent = Response.createSuccessResponseString();
			break;			
		default:
			throw new Exception(String.format("Unrecognized element action: %s", action.getActionType()));
		}
		return retContent;
	}

	public String takeElementAction(String uuid, String jsonStr) throws Exception {
		return this.getElementHandler().takeElementAction(uuid, jsonStr);
	}
	
	public String takeMultiElementAction(String uuid, String jsonStr) throws Exception {
		return this.getElementHandler().takeMultiElementAction(uuid, jsonStr);
	}

	public String takeScreeshot() throws Exception {
		return this.automator.getScreenshoter().takeScreenshot();
	}

}

class Action{
	String action;
	String args;
}
