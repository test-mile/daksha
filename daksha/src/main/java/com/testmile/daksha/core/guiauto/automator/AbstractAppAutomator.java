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

package com.testmile.daksha.core.guiauto.automator;

import com.testmile.daksha.core.guiauto.alert.DefaultAlert;
import com.testmile.daksha.core.guiauto.dropdown.DefaultDropDown;
import com.testmile.daksha.core.guiauto.element.DefaultGuiElement;
import com.testmile.daksha.core.guiauto.frame.DefaultFrame;
import com.testmile.daksha.core.guiauto.multielement.DefaultGuiMultiElement;
import com.testmile.daksha.core.guiauto.radiogroup.DefaultRadioGroup;
import com.testmile.daksha.core.guiauto.setu.SetuGuiAutoRequester;
import com.testmile.daksha.core.guiauto.window.DefaultChildWindow;
import com.testmile.daksha.core.setu.DefaultSetuObject;
import com.testmile.daksha.core.setu.Response;
import com.testmile.daksha.core.setu.SetuSvcRequester;
import com.testmile.daksha.tpi.guiauto.Alert;
import com.testmile.daksha.tpi.guiauto.ChildWindow;
import com.testmile.daksha.tpi.guiauto.DropDown;
import com.testmile.daksha.tpi.guiauto.Frame;
import com.testmile.daksha.tpi.guiauto.GuiElement;
import com.testmile.daksha.tpi.guiauto.GuiMultiElement;
import com.testmile.daksha.tpi.guiauto.MainWindow;
import com.testmile.daksha.tpi.guiauto.RadioGroup;
import com.testmile.daksha.tpi.guiauto.With;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.daksha.tpi.test.TestConfig;

public class AbstractAppAutomator extends DefaultSetuObject implements AppAutomator {
	protected SetuSvcRequester setuClient;
	protected MainWindow mainWindow;
	protected String baseUri;
	private GuiAutomationContext autoContext;
	private TestConfig config;
	
	public AbstractAppAutomator(String baseUri) {
		super();
		this.setuClient = new SetuGuiAutoRequester();
		this.baseUri = baseUri;
	}

	public AbstractAppAutomator(String baseUri, TestConfig config) {
		this(baseUri);
		this.setConfig(config);
		this.setTestSessionSetuId(config.getTestSessionSetuId());
	}
	
	protected void setAutomationContext(GuiAutomationContext context) {
		this.autoContext = context;
	}
	
	public GuiAutomationContext getAutomationContext() {
		return autoContext;
	}

	public SetuSvcRequester getSetuClient() {
		return this.setuClient;
	}

	private Response takeAction(GuiAppAutomatorAction action) throws Exception {
		return this.setuClient.post(baseUri + "action", action);
	}

	protected String takeElementFindingAction(GuiAppAutomatorAction action) throws Exception {
		Response response = takeAction(action);
		return (String) response.getData().get("elementSetuId");			
	}

	private String createGenericElement(GuiAppAutomatorActionType actionType, With with, String value) throws Exception {
		GuiAppAutomatorAction action = new GuiAppAutomatorAction(this, actionType);
		action.addArg("withType", with);
		action.addArg("withValue", value);
		return takeElementFindingAction(action);	
	}

	@Override
	public GuiElement element(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAppAutomatorActionType.CREATE_ELEMENT, with, value);
		return new DefaultGuiElement(this, elemSetuId);
	}

	@Override
	public GuiMultiElement multiElement(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAppAutomatorActionType.CREATE_MULTIELEMENT, with, value);
		return new DefaultGuiMultiElement(this, elemSetuId);
	}

	@Override
	public DropDown dropdown(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAppAutomatorActionType.CREATE_DROPDOWN, with, value);
		return new DefaultDropDown(this, elemSetuId);
	}

	@Override
	public RadioGroup radioGroup(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAppAutomatorActionType.CREATE_RADIOGROUP, with, value);
		return new DefaultRadioGroup(this, elemSetuId);
	}

	@Override
	public Frame frame(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAppAutomatorActionType.CREATE_FRAME, with, value);
		return new DefaultFrame(this, elemSetuId);
	}

	@Override
	public Alert alert() throws Exception {
		GuiAppAutomatorAction action = new GuiAppAutomatorAction(this, GuiAppAutomatorActionType.CREATE_ALERT);
		return new DefaultAlert(this, takeElementFindingAction(action));
	}

	@Override
	public ChildWindow childWindow(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(GuiAppAutomatorActionType.CREATE_CHILD_WINDOW, with, value);
		return new DefaultChildWindow(this, elemSetuId);
	}

	@Override
	public MainWindow mainWindow() throws Exception {
		return this.mainWindow;
	}

	@Override
	public ChildWindow newChildWindow() throws Exception {
		GuiAppAutomatorAction action = new GuiAppAutomatorAction(this, GuiAppAutomatorActionType.CREATE_NEW_CHILD_WINDOW);
		return new DefaultChildWindow (this, takeElementFindingAction(action));
	}

	@Override
	public void goToUrl(String url) throws Exception {
		GuiAppAutomatorAction action = new GuiAppAutomatorAction(this, GuiAppAutomatorActionType.NAVIGATE_BROWSER);
		action.addArg("navType", BrowserNavigationType.TO.toString());
		action.addArg("url", url);
		this.takeAction(action);
	}

	@Override
	public void executeJavaScript(String script) throws Exception {
		GuiAppAutomatorAction action = new GuiAppAutomatorAction(this, GuiAppAutomatorActionType.EXECUTE_JAVASCRIPT);
		action.addArg("script", script);
		this.takeAction(action);
	}

	@Override
	public void closeAllChildWindows() throws Exception {
		GuiAppAutomatorAction action = new GuiAppAutomatorAction(this, GuiAppAutomatorActionType.CLOSE_ALL_CHILD_WINDOWS);
		this.takeAction(action);
	}

	public TestConfig getConfig() {
		return config;
	}

	protected void setConfig(TestConfig config) {
		this.config = config;
	}

}