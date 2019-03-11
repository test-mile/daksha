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

package com.testmile.setu.actor.guiauto.adapter.sikuli;

import java.util.Map;

import com.testmile.setu.actor.guiauto.adapter.GuiAutoAdapter;
import com.testmile.setu.actor.guiauto.commander.driver.DriverContainer;
import com.testmile.trishanku.tpi.setu.actor.ActorAction;

public class SikuliAdapter implements  GuiAutoAdapter {
	private DriverContainer automator = null;
	private String id = null;
//	private GuiElementHandler elementHandler = new GuiElementHandler();

	public SikuliAdapter(String setuId) throws Exception {
		id = setuId;
	}
	
	public String getSetuId() {
		return this.id;
	}
//
//	private GuiElementHandler getElementHandler() {
//		return this.elementHandler;
//	}
//	
//	public void quit() throws Exception {
//		this.automator.quit();
//	}
//
//	public String takeAction(String actionStr, Map<String,Object> args) throws Exception {
//		//AutomatorAction action = new AutomatorAction(jsonStr);
//		String retContent = null;
//		switch(AutomatorActionType.valueOf(actionStr)) {
//		case LAUNCH:
//			automator = GuiAutomatorFactory.createAutomator(args);
//			return Response.createSuccessResponseString();
//		case QUIT:
//			this.automator.quit();
//			return Response.createSuccessResponseString();
//		case GO_TO_URL:
//			System.out.println();
//			this.automator.getBrowserHandler().goTo((String) args.get("url"));
//			retContent = Response.createSuccessResponseString();
//			break;
//		case FIND_ELEMENT:
//			DriverElementDispatcher foundElement = this.automator.getElementFinder().find(
//					(String) args.get("withType"),
//					(String) args.get("withValue")
//			);
//			String setu_id = (String) args.get("elementSetuId");
//			this.getElementHandler().registerElement(setu_id,  foundElement);
//			retContent = Response.createSuccessResponseString();
//			break;
//		case FIND_MULTIELEMENT:
//			GuiMultiElement mElement = this.automator.getElementFinder().findAll(
//					(String) args.get("withType"),
//					(String) args.get("withValue")
//			);
//			getElementHandler().registerMultiElement((String) args.get("elementSetuId"), mElement);
//			retContent = Response.createSuccessResponseString("instanceCount", mElement.getInstanceCount());
//			break;
//		case FOCUS_ON_FRAME:
//			DriverElementDispatcher fElement;
//			Object isInstanceAction = args.get("isInstanceAction");
//			if ((isInstanceAction != null) && ((boolean) isInstanceAction)){
//				GuiMultiElement mfElement = this.getElementHandler().getMultiElementForSetuId((String) args.get("elementSetuId"));
//				fElement = mfElement.getInstanceAtIndex(((Number) args.get("instanceIndex")).intValue());
//			} else {
//				fElement = this.getElementHandler().getElementForSetuId((String) args.get("elementSetuId"));
//			}
//			this.automator.getFrameHandler().focusOnFrame(fElement);
//			retContent = Response.createSuccessResponseString();
//			break;
//		case FOCUS_ON_PARENT_FRAME:
//			this.automator.getFrameHandler().focusOnParentFrame();
//			retContent = Response.createSuccessResponseString();
//			break;
//		case FOCUS_ON_DOM_ROOT:
//			this.automator.getFrameHandler().focusOnDomRoot();
//			retContent = Response.createSuccessResponseString();
//			break;
//		case CLOSE_CURRENT_WINDOW:
//			this.automator.getWindowHandler().closeCurrentWindow();
//			retContent = Response.createSuccessResponseString();
//			break;
//		case GET_ALL_WINDOW_HANDLES:
//			Set<String> handles = this.automator.getWindowHandler().getAllWindowHandles();
//			retContent = Response.createSuccessResponseString("result", handles);
//			break;
//		case GET_CURRENT_WINDOW_TITLE:
//			String title = this.automator.getWindowHandler().getTitle();
//			retContent = Response.createSuccessResponseString("result", title);
//			break;
//		case GET_CURRENT_WINDOW_HANDLE:
//			String handle = this.automator.getWindowHandler().getCurrentWindowHandle();
//			retContent = Response.createSuccessResponseString("result", handle);
//			break;
//		case GET_CURRENT_WINDOW_SIZE:
//			Map<String,Integer> size = this.automator.getWindowHandler().getCurrentWindowSize();
//			retContent = Response.createSuccessResponseString("result", size);
//			break;
//		case MAXIMIZE_CURRENT_WINDOW:
//			this.automator.getWindowHandler().maximizeWindow();
//			retContent = Response.createSuccessResponseString();
//			break;
//		case SET_CURRENT_WINDOW_SIZE:
//			this.automator.getWindowHandler().setWindowSize(
//					(Integer) args.get("width"), (Integer) args.get("height")
//			);
//			retContent = Response.createSuccessResponseString();
//			break;
//		case FOCUS_ON_WINDOW:
//			this.automator.getWindowHandler().focusOnWindow((String) args.get("handle"));
//			retContent = Response.createSuccessResponseString();
//			break;
//		case EXECUTE_JAVASCRIPT:
//			this.automator.getJsExecutor().executeScript((String) args.get("script"));
//			retContent = Response.createSuccessResponseString();
//			break;
//		case IS_BROWSER_ALERT_PRESENT:
//			boolean alertPresent = this.automator.getAlertHandler().isWebAlertPresent();
//			retContent = Response.createSuccessResponseString("result", alertPresent);
//			break;			
//		case CONFIRM_BROWSER_ALERT:
//			this.automator.getAlertHandler().confirmWebAlert();
//			retContent = Response.createSuccessResponseString();
//			break;
//		case DISMISS_BROWSER_ALERT:
//			this.automator.getAlertHandler().dismissWebAlert();
//			retContent = Response.createSuccessResponseString();
//			break;
//		case SEND_TEXT_TO_BROWSER_ALERT:
//			this.automator.getAlertHandler().sendTextToWebAlert((String) args.get("text"));
//			retContent = Response.createSuccessResponseString();
//			break;
//		case GET_TEXT_FROM_BROWSER_ALERT:
//			String alertText = this.automator.getAlertHandler().getTextFromWebAlert();
//			retContent = Response.createSuccessResponseString("text", alertText);
//			break;
//		case GET_CURRENT_MOBILE_VIEW_CONTEXT:
//			String viewContext = this.automator.getViewHandler().getCurrentViewContext();
//			retContent = Response.createSuccessResponseString("result", viewContext);
//			break;
//		case GET_ALL_MOBILE_VIEW_CONTEXTS:
//			Set<String> viewContexts = this.automator.getViewHandler().getAllViewContexts();
//			retContent = Response.createSuccessResponseString("result", viewContexts);		
//			break;
//		case FOCUS_ON_MOBILE_VIEW_CONTEXT:
//			this.automator.getViewHandler().switchToViewContext((String) args.get("viewContext"));
//			retContent = Response.createSuccessResponseString();
//			break;			
//		default:
//			throw new Exception(String.format("Unrecognized automator action: %s", actionStr));
//		}
//		return retContent;
//	}
//
//	public String takeElementAction(String elemId, String actionStr, Map<String,Object> args) throws Exception {
//		return this.getElementHandler().takeElementAction(elemId, actionStr, args);
//	}
//
//	public String takeScreeshot() throws Exception {
//		return this.automator.getScreenshoter().takeScreenshot();
//	}

	@Override
	public String takeElementAction(String elemId, ActorAction action) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String launchAutomator(Map<String, Object> args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String takeAction(ActorAction action) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}