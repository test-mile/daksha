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

package com.testmile.setu.actor.guiauto.adapter.driver;

import java.util.Map;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.actor.guiauto.adapter.GuiAutoAdapter;
import com.testmile.setu.actor.guiauto.commander.driver.DriverCommandUtils;
import com.testmile.setu.actor.guiauto.commander.driver.DriverContainer;
import com.testmile.setu.actor.guiauto.commander.driver.DriverElementContainer;
import com.testmile.setu.actor.guiauto.core.GuiAutomatorActionType;
import com.testmile.setu.actor.guiauto.core.GuiMultiElement;
import com.testmile.trishanku.tpi.setu.actor.ActorAction;
import com.testmile.trishanku.tpi.setu.actor.ServerResponse;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

class AbstractDriverTranslator {
	protected String success() {
		return ServerResponse.createSuccessResponseString();
	}
	
	protected String success(String name, Object value) {
		return ServerResponse.createSuccessResponseString(name, value);
	}
	
	protected String successResult(Object value) {
		return ServerResponse.createSuccessResponseString("result", value);
	}
}

public abstract class DriverAdapter<T,E> extends AbstractDriverTranslator implements GuiAutoAdapter {
	private DriverContainer<T,E> automator = null;
	private String automatorName;
	private String id = null;
	private DriverElementAdapter<T,E> elementHandler = new DriverElementAdapter<T,E>();

	public DriverAdapter(String setuId, String automatorName) throws Exception {
		id = setuId;
		this.automatorName = automatorName;
	}
	
	public static GuiAutoAdapter Selenium(String setuId, String automatorName) throws Exception {
		return new SeleniumAdapter(setuId, automatorName);
	}
	
	public static GuiAutoAdapter Appium(String setuId, String automatorName) throws Exception {
		return new AppiumAdapter(setuId, automatorName);
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.actor.core.guiauto.translator.Translator#getSetuId()
	 */
	@Override
	public String getSetuId() {
		return this.id;
	}

	private DriverElementAdapter<T,E> getElementHandler() {
		return this.elementHandler;
	}
	
	protected void setDispatcher(DriverContainer<T,E> dispatcher) {
		this.automator = dispatcher;
	}
	
	protected DriverContainer<T,E> getDispatcher() {
		return this.automator;
	}
	
	protected abstract void loadDriverContainer(String name, Map<String, Object> args) throws Exception;
	
	@Override
	public String launchAutomator(Map<String,Object> args) throws Exception {
		this.loadDriverContainer(this.automatorName, args);
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
			DriverCommandUtils.quit(this.automator.asWebDriver());
			return success();
		case GO_TO_URL:
			System.out.println(action.value("url").asString());
			DriverCommandUtils.goTo(this.automator.asWebDriver(), action.value("url").asString());
			return success();
		case FIND_ELEMENT:
			DriverElementContainer<T,E> element = this.automator.findElement(action.strValue("withType"), action.strValue("withValue"));
			String setu_id = action.getElementId();
			this.getElementHandler().registerElement(setu_id,  element);
			return success();
		case FIND_MULTIELEMENT:
			GuiMultiElement<T,E> mElement = this.automator.findElements(action.strValue("withType"), action.strValue("withValue")); 
			getElementHandler().registerMultiElement(action.getElementId(), mElement);
			return success("instanceCount", mElement.getInstanceCount());
		case FOCUS_ON_FRAME:
			DriverElementContainer<T,E> fElement;
			if (action.isInstanceAction()){
				GuiMultiElement<T,E> mfElement = this.getElementHandler().getMultiElementForSetuId(action.strValue("elementSetuId"));
				fElement = mfElement.getInstanceAtIndex(action.intValue("instanceIndex"));
			} else {
				fElement = this.getElementHandler().getElementForSetuId(action.strValue("elementSetuId"));
			}
			
			DriverCommandUtils.focusOnFrame(this.automator.asWebDriver(), fElement.asWebElement());
			return this.success();
		case FOCUS_ON_PARENT_FRAME:
			DriverCommandUtils.focusOnParentFrame(this.automator.asWebDriver());
			return this.success();
		case FOCUS_ON_DOM_ROOT:
			DriverCommandUtils.focusOnDomRoot(this.automator.asWebDriver());
			return this.success();
		case CLOSE_CURRENT_WINDOW:
			DriverCommandUtils.closeCurrentWindow(this.automator.asWebDriver());
			return this.success();
		case GET_ALL_WINDOW_HANDLES:
			Set<String> handles = DriverCommandUtils.getAllWindowHandles(this.automator.asWebDriver());
			return successResult(handles);
		case GET_CURRENT_WINDOW_TITLE:
			String title = DriverCommandUtils.getTitle(this.automator.asWebDriver());
			return successResult(title);
		case GET_CURRENT_WINDOW_HANDLE:
			String handle = DriverCommandUtils.getCurrentWindowHandle(this.automator.asWebDriver());
			return successResult(handle);
		case GET_CURRENT_WINDOW_SIZE:
			int[] size = DriverCommandUtils.getCurrentWindowSize(this.automator.asWebDriver());
			return successResult(size);
		case MAXIMIZE_CURRENT_WINDOW:
			DriverCommandUtils.maximizeWindow(this.automator.asWebDriver());
			return this.success();
		case SET_CURRENT_WINDOW_SIZE:
			DriverCommandUtils.setWindowSize(this.automator.asWebDriver(), action.intValue("width"), action.intValue("height"));
			return this.success();
		case FOCUS_ON_WINDOW:
			DriverCommandUtils.focusOnWindow(this.automator.asWebDriver(), action.strValue("handle"));
			return this.success();
		case EXECUTE_JAVASCRIPT:
			DriverCommandUtils.executeJavaScript(this.automator.asWebDriver(), action.strValue("script"));
			return this.success();
		case IS_WEB_ALERT_PRESENT:
			boolean result = DriverCommandUtils.isWebAlertPresent(this.automator.asWebDriver());
			return successResult(result);		
		case CONFIRM_WEB_ALERT:
			DriverCommandUtils.confirmWebAlert(this.automator.asWebDriver());
			return success();	
		case DISMISS_WEB_ALERT:
			DriverCommandUtils.dismissWebAlert(this.automator.asWebDriver());
			return success();	
		case SEND_TEXT_TO_WEB_ALERT:
			DriverCommandUtils.sendTextToWebAlert(this.automator.asWebDriver(), action.strValue("text"));
			return success();
		case GET_TEXT_FROM_WEB_ALERT:
			String alertText = DriverCommandUtils.getTextFromWebAlert(this.automator.asWebDriver());
			return successResult(alertText);
		case GET_CURRENT_MOBILE_VIEW_CONTEXT:
			String viewContext = DriverCommandUtils.getCurrentMobileViewContext(this.automator.asAppiumDriver());
			return successResult(viewContext);
		case GET_ALL_MOBILE_VIEW_CONTEXTS:
			Set<String> viewContexts = DriverCommandUtils.getAllMobileViewContexts(this.automator.asAppiumDriver());
			return successResult(viewContexts);	
		case FOCUS_ON_MOBILE_VIEW_CONTEXT:
			DriverCommandUtils.focusOnMobileViewContext(this.automator.asAppiumDriver(), action.strValue("viewContext"));
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

class SeleniumAdapter extends DriverAdapter<WebDriver, WebElement>{
	
	public SeleniumAdapter(String setuId, String automatorName) throws Exception {
		super(setuId, automatorName);
	}

	protected void loadDriverContainer(String name, Map<String, Object> args) throws Exception {
		this.setDispatcher(DriverContainer.Selenium(new SetuDriverConfig(args)));
	}
}

class AppiumAdapter extends DriverAdapter<AppiumDriver<MobileElement>,MobileElement>{
	
	public AppiumAdapter(String setuId, String automatorName) throws Exception {
		super(setuId, automatorName);
	}

	protected void loadDriverContainer(String name, Map<String, Object> args) throws Exception {
		this.setDispatcher(DriverContainer.Appium(new SetuDriverConfig(args)));
	}
}