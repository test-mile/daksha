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

package com.testmile.arjuna.lib.setu.guiauto.requester.automator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.testmile.arjuna.lib.enums.GuiAutomationContext;
import com.testmile.arjuna.lib.setu.core.requester.config.SetuActionType;
import com.testmile.arjuna.lib.setu.core.requester.connector.BaseSetuObject;
import com.testmile.arjuna.lib.setu.core.requester.connector.SetuArg;
import com.testmile.arjuna.lib.setu.core.requester.connector.SetuResponse;
import com.testmile.arjuna.lib.setu.guiauto.requester.component.GuiAutoComponentFactory;
import com.testmile.arjuna.lib.setu.testsession.requester.TestSession;
import com.testmile.arjuna.tpi.guiauto.With;
import com.testmile.arjuna.tpi.guiauto.component.Alert;
import com.testmile.arjuna.tpi.guiauto.component.Browser;
import com.testmile.arjuna.tpi.guiauto.component.ChildWindow;
import com.testmile.arjuna.tpi.guiauto.component.DomRoot;
import com.testmile.arjuna.tpi.guiauto.component.DropDown;
import com.testmile.arjuna.tpi.guiauto.component.Frame;
import com.testmile.arjuna.tpi.guiauto.component.GuiElement;
import com.testmile.arjuna.tpi.guiauto.component.GuiMultiElement;
import com.testmile.arjuna.tpi.guiauto.component.MainWindow;
import com.testmile.arjuna.tpi.guiauto.component.RadioGroup;
import com.testmile.arjuna.tpi.test.TestConfig;

public class AbstractAppAutomator extends BaseSetuObject implements AppAutomator {
	private DomRoot domRoot;
	private MainWindow mainWindow;
	private Browser browser;
	private GuiAutomationContext autoContext;
	private TestSession testSession;
	private TestConfig config;
	
	public AbstractAppAutomator() {
		super();
	}

	public AbstractAppAutomator(TestConfig config) {
		this.setConfig(config);
		this.testSession = config.getTestSession();
		this.setTestSessionSetuIdArg(this.testSession.getSetuId());
	}
	
	protected void setAutomationContext(GuiAutomationContext context) {
		this.autoContext = context;
	}
	
	public GuiAutomationContext getAutomationContext() {
		return autoContext;
	}
	
	@Override
	public boolean isGui() {
		return false;
	}

	protected String takeElementFindingAction(SetuActionType actionType, SetuArg... args) throws Exception {
		SetuResponse response = this.sendRequest(actionType, args);
		return response.getValueForElementSetuId();		
	}

	private String createGenericElement(SetuActionType actionType, With... locators) throws Exception {
		List<Map<String,Object>> arg = new ArrayList<Map<String,Object>>();
		for(With locator: locators) {
			arg.add(locator.asMap());
		}
		return this.takeElementFindingAction(
				actionType,
				SetuArg.arg("locators", arg)
		);	
	}

	@Override
	public GuiElement element(With... locators) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_ELEMENT, locators);
		return GuiAutoComponentFactory.Element(this.testSession, this, elemSetuId);
	}

	@Override
	public GuiMultiElement multiElement(With... locators) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_MULTIELEMENT, locators);
		return GuiAutoComponentFactory.MultiElement(this.testSession, this, elemSetuId);
	}

	@Override
	public DropDown dropdown(With... locators) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_DROPDOWN, locators);
		return GuiAutoComponentFactory.DropDown(this.testSession, this, elemSetuId);
	}

	@Override
	public RadioGroup radioGroup(With... locators) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_RADIOGROUP, locators);
		return GuiAutoComponentFactory.RadioGroup(this.testSession, this, elemSetuId);
	}

	@Override
	public Alert alert() throws Exception {
		String elemSetuId = takeElementFindingAction(SetuActionType.GUIAUTO_CREATE_ALERT);
		return GuiAutoComponentFactory.Alert(this.testSession, this, elemSetuId);
	}
	
	@Override
	public Frame frame(With... locators) throws Exception {
		return this.domRoot().frame(locators);
	}
	
	@Override
	public ChildWindow childWindow(With... locators) throws Exception {
		return this.mainWindow.childWindow(locators);
	}
	
	@Override
	public ChildWindow latestChildWindow() throws Exception {
		return this.mainWindow.latestChildWindow();
	}

	@Override
	public void closeAllChildWindows() throws Exception {
		this.mainWindow.closeAllChildWindows();
	}
	
	@Override
	public MainWindow mainWindow() throws Exception {
		return this.mainWindow;
	}
	
	protected void setMainWindow(MainWindow win) throws Exception {
		this.mainWindow = win;
	}

	public TestConfig getConfig() {
		return config;
	}

	protected void setConfig(TestConfig config) {
		this.config = config;
	}
	
	@Override
	public DomRoot domRoot() {
		return domRoot;
	}

	protected void setDomRoot(DomRoot domRoot) {
		this.domRoot = domRoot;
	}
	
	public TestSession getTestSession() {
		return this.testSession;
	}

	@Override
	public Browser browser() {
		return this.browser;
	}
	
	protected void setBrowser(Browser browser) throws Exception {
		this.browser = browser;
	}
	
	@Override
	public void enableSlowMotion(boolean on, int interval) throws Exception {
		this.sendRequest(
				SetuActionType.GUIAUTO_SET_SLOMO,
				SetuArg.arg("on", on),
				SetuArg.arg("interval", interval)
		);
	}
	
	@Override
	public void enableSlowMotion(boolean on) throws Exception {
		this.sendRequest(
				SetuActionType.GUIAUTO_SET_SLOMO,
				SetuArg.arg("on", on)
		);
	}
	
	@Override
	public void executeJavaScript(String script) throws Exception {
		this.sendRequest(SetuActionType.GUIAUTO_BROWSER_EXECUTE_JAVASCRIPT, SetuArg.arg("script", script));
	}

}