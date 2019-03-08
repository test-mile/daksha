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

package com.testmile.setu.actor.core.guiauto.translator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.actor.ActorAction;
import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.core.guiauto.dispatcher.DriverDispatchCommander;
import com.testmile.setu.actor.core.guiauto.dispatcher.DriverElementDispatchCommander;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

class AbstractDriverTranslator {
	protected String success() {
		return Response.createSuccessResponseString();
	}
	
	protected String success(String name, Object value) {
		return Response.createSuccessResponseString(name, value);
	}
	
	protected String successResult(Object value) {
		return Response.createSuccessResponseString("result", value);
	}
}

public abstract class DriverTranslator<T,E> extends AbstractDriverTranslator implements GuiAutoTranslator {
	private DriverDispatcher<T,E> automator = null;
	private String automatorName;
	private String id = null;
	private DriverGuiElementTranslator<T,E> elementHandler = new DriverGuiElementTranslator();

	public DriverTranslator(String setuId, String automatorName) throws Exception {
		id = setuId;
		this.automatorName = automatorName;
	}
	
	public static GuiAutoTranslator seleniumTranslator(String setuId, String automatorName) throws Exception {
		return new SeleniumTranslator(setuId, automatorName);
	}
	
	public static GuiAutoTranslator appiumTranslator(String setuId, String automatorName) throws Exception {
		return new AppiumTranslator(setuId, automatorName);
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.actor.core.guiauto.translator.Translator#getSetuId()
	 */
	@Override
	public String getSetuId() {
		return this.id;
	}

	private DriverGuiElementTranslator getElementHandler() {
		return this.elementHandler;
	}
	
	protected void setDispatcher(DriverDispatcher<T,E> dispatcher) {
		this.automator = dispatcher;
	}
	
	protected DriverDispatcher<T,E> getDispatcher() {
		return this.automator;
	}
	
	protected abstract void loadDispatcher(String name, Map<String, Object> args) throws Exception;
	
	@Override
	public String launchAutomator(Map<String,Object> args) throws Exception {
		this.loadDispatcher(this.automatorName, args);
		return this.success();
	}


	
	/* (non-Javadoc)
	 * @see com.testmile.setu.actor.core.guiauto.translator.Translator#takeAction(java.lang.String, java.util.Map)
	 */
	@Override
	public String takeAction(ActorAction action) throws Exception {
		//AutomatorAction action = new AutomatorAction(jsonStr);
		String retContent = null;
		switch(GuiAutomatorActionType.valueOf(action.getActionString())) {
		case QUIT:
			DriverDispatchCommander.quit(this.automator.asWebDriver());
			return success();
		case GO_TO_URL:
			System.out.println(action.value("url").asString());
			DriverDispatchCommander.goTo(this.automator.asWebDriver(), action.value("url").asString());
			return success();
		case FIND_ELEMENT:
			DriverElementDispatcher<T,E> element = this.automator.findElement(action.strValue("withType"), action.strValue("withValue"));
			String setu_id = action.getElementId();
			this.getElementHandler().registerElement(setu_id,  element);
			return success();
		case FIND_MULTIELEMENT:
			GuiMultiElement<T,E> mElement = this.automator.findElements(action.strValue("withType"), action.strValue("withValue")); 
			getElementHandler().registerMultiElement(action.getElementId(), mElement);
			return success("instanceCount", mElement.getInstanceCount());
		case FOCUS_ON_FRAME:
			DriverElementDispatcher<T,E> fElement;
			if (action.isInstanceAction()){
				GuiMultiElement<T,E> mfElement = this.getElementHandler().getMultiElementForSetuId(action.strValue("elementSetuId"));
				fElement = mfElement.getInstanceAtIndex(action.intValue("instanceIndex"));
			} else {
				fElement = this.getElementHandler().getElementForSetuId(action.strValue("elementSetuId"));
			}
			
			DriverDispatchCommander.focusOnFrame(this.automator.asWebDriver(), fElement.asWebElement());
			return this.success();
		case FOCUS_ON_PARENT_FRAME:
			DriverDispatchCommander.focusOnParentFrame(this.automator.asWebDriver());
			return this.success();
		case FOCUS_ON_DOM_ROOT:
			DriverDispatchCommander.focusOnDomRoot(this.automator.asWebDriver());
			return this.success();
		case CLOSE_CURRENT_WINDOW:
			DriverDispatchCommander.closeCurrentWindow(this.automator.asWebDriver());
			return this.success();
		case GET_ALL_WINDOW_HANDLES:
			Set<String> handles = DriverDispatchCommander.getAllWindowHandles(this.automator.asWebDriver());
			return successResult(handles);
		case GET_CURRENT_WINDOW_TITLE:
			String title = DriverDispatchCommander.getTitle(this.automator.asWebDriver());
			return successResult(title);
		case GET_CURRENT_WINDOW_HANDLE:
			String handle = DriverDispatchCommander.getCurrentWindowHandle(this.automator.asWebDriver());
			return successResult(handle);
		case GET_CURRENT_WINDOW_SIZE:
			int[] size = DriverDispatchCommander.getCurrentWindowSize(this.automator.asWebDriver());
			return successResult(size);
		case MAXIMIZE_CURRENT_WINDOW:
			DriverDispatchCommander.maximizeWindow(this.automator.asWebDriver());
			return this.success();
		case SET_CURRENT_WINDOW_SIZE:
			DriverDispatchCommander.setWindowSize(this.automator.asWebDriver(), action.intValue("width"), action.intValue("height"));
			return this.success();
		case FOCUS_ON_WINDOW:
			DriverDispatchCommander.focusOnWindow(this.automator.asWebDriver(), action.strValue("handle"));
			return this.success();
		case EXECUTE_JAVASCRIPT:
			DriverDispatchCommander.executeJavaScript(this.automator.asWebDriver(), action.strValue("script"));
			return this.success();
		case IS_BROWSER_ALERT_PRESENT:
			boolean result = DriverDispatchCommander.isWebAlertPresent(this.automator.asWebDriver());
			return successResult(result);		
		case CONFIRM_BROWSER_ALERT:
			DriverDispatchCommander.confirmWebAlert(this.automator.asWebDriver());
			return success();	
		case DISMISS_BROWSER_ALERT:
			DriverDispatchCommander.dismissWebAlert(this.automator.asWebDriver());
			return success();	
		case SEND_TEXT_TO_BROWSER_ALERT:
			DriverDispatchCommander.sendTextToWebAlert(this.automator.asWebDriver(), action.strValue("text"));
			return success();
		case GET_TEXT_FROM_BROWSER_ALERT:
			String alertText = DriverDispatchCommander.getTextFromWebAlert(this.automator.asWebDriver());
			return successResult(alertText);
		case GET_CURRENT_MOBILE_VIEW_CONTEXT:
			String viewContext = DriverDispatchCommander.getCurrentMobileViewContext(this.automator.asAppiumDriver());
			return successResult(viewContext);
		case GET_ALL_MOBILE_VIEW_CONTEXTS:
			Set<String> viewContexts = DriverDispatchCommander.getAllMobileViewContexts(this.automator.asAppiumDriver());
			return successResult(viewContexts);	
		case FOCUS_ON_MOBILE_VIEW_CONTEXT:
			DriverDispatchCommander.focusOnMobileViewContext(this.automator.asAppiumDriver(), action.strValue("viewContext"));
			return success();		
		default:
			throw new Exception(String.format("Unrecognized automator action: %s", action.getActionString()));
		}
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.actor.core.guiauto.translator.Translator#takeElementAction(java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public String takeElementAction(String elemId, ActorAction action) throws Exception {
		return this.getElementHandler().takeElementAction(elemId, action);
	}
}

class SeleniumTranslator extends DriverTranslator<WebDriver, WebElement>{
	
	public SeleniumTranslator(String setuId, String automatorName) throws Exception {
		super(setuId, automatorName);
	}

	protected void loadDispatcher(String name, Map<String, Object> args) throws Exception {
		this.setDispatcher(DriverDispatcher.seleniumDispatcher(new SetuActorConfig(args)));
	}
}

class AppiumTranslator extends DriverTranslator<AppiumDriver<MobileElement>,MobileElement>{
	
	public AppiumTranslator(String setuId, String automatorName) throws Exception {
		super(setuId, automatorName);
	}

	protected void loadDispatcher(String name, Map<String, Object> args) throws Exception {
		this.setDispatcher(DriverDispatcher.appiumDispatcher(new SetuActorConfig(args)));
	}
}

class DriverGuiElementTranslator<T,E> extends AbstractDriverTranslator{
	private Map<String, DriverElementDispatcher<T,E>> elementMap = new HashMap<String, DriverElementDispatcher<T,E>>();
	private Map<String, GuiMultiElement<T,E>> melementMap = new HashMap<String, GuiMultiElement<T,E>>();

	public void registerElement(String uuid, DriverElementDispatcher<T,E> element) {
		System.out.println("registering: " + uuid);
		this.elementMap.put(uuid, element);
	}
	
	public void registerMultiElement(String uuid, GuiMultiElement<T,E> element) {
		this.melementMap.put(uuid, element);
	}

	public void deregisterMultiElementForSetuId(String uuid) {
		this.melementMap.remove(uuid);
	}
	
	public DriverElementDispatcher<T,E> getElementForSetuId(String uuid) throws Exception {
		System.out.println("retrieving: " + uuid);
		if (!this.elementMap.containsKey(uuid)) {
			throw new Exception(String.format("Element with Setu ID: %s does not exist in agent.", uuid));
		}
		return this.elementMap.get(uuid);
	}
	
	public GuiMultiElement<T,E> getMultiElementForSetuId(String uuid) throws Exception {
		if (!this.melementMap.containsKey(uuid)) {
			throw new Exception(String.format("MultiElement with Setu ID: %s does not exist in agent.", uuid));
		}
		return this.melementMap.get(uuid);
	}
	
	private String takeSingleElementAction(DriverElementDispatcher<T,E> element, ActorAction action) throws Exception {
		String retContent = null;
		boolean result = false;
		String textResult = null;
		switch(GuiElementActionType.valueOf(action.getActionString())) {
		case FIND_ELEMENT:
			DriverElementDispatcher<T,E> foundElement = element.findElement(action.strValue("withType"), action.strValue("withValue"));
			String setuId = action.strValue("childElementSetuId");
			registerElement(setuId,  foundElement);
			return success();
		case FIND_MULTIELEMENT:
			GuiMultiElement<T,E> mElement = element.findElements(action.strValue("withType"), action.strValue("withValue"));
			String mSetuId = action.strValue("childElementSetuId");
			registerMultiElement(mSetuId,  mElement);
			return this.success("instanceCount", mElement.getInstanceCount());
		case CLICK:
			DriverElementDispatchCommander.click(element.asWebElement());
			return success();
		case CLEAR_TEXT:
			DriverElementDispatchCommander.clearText(element.asWebElement());
			return success();
		case SEND_TEXT:
			DriverElementDispatchCommander.sendText(element.asWebElement(), action.strValue("text"));
			return success();
		case IS_SELECTED:
			result = DriverElementDispatchCommander.isSelected(element.asWebElement());
			return successResult(result);
		case IS_VISIBLE:
			result = DriverElementDispatchCommander.isVisible(element.asWebElement());
			return successResult(result);
		case IS_CLICKABLE:
			result = DriverElementDispatchCommander.isClickable(element.asWebElement());
			return successResult(result);
		case GET_TAG_NAME:
			textResult = DriverElementDispatchCommander.getTagName(element.asWebElement());
			return successResult(textResult);
		case GET_ATTR_VALUE:
			textResult = DriverElementDispatchCommander.getAttribute(element.asWebElement(), action.strValue("attr"));
			return successResult(textResult);
		case GET_TEXT_CONTENT:
			textResult = DriverElementDispatchCommander.getTextContent(element.asWebElement());
			return successResult(textResult);
		default:
			throw new Exception(String.format("Unrecognized element action: %s", action.getActionString()));
		}	
	}

	public String takeElementAction(String elemSetuId, ActorAction action) throws Exception {
		DriverElementDispatcher<T,E> element;
		if (action.isInstanceAction()) {
			GuiMultiElement<T,E> me = getMultiElementForSetuId(elemSetuId);
			element = me.getInstanceAtIndex(action.intValue("instanceIndex"));
		} else {
			element = getElementForSetuId(elemSetuId);
		}
		return takeSingleElementAction(element, action);
	}

}
