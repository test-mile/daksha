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

package com.testmile.setu.requester.guiauto.automator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.testmile.setu.requester.config.SetuActionType;
import com.testmile.setu.requester.config.TestConfig;
import com.testmile.setu.requester.connector.BaseSetuObject;
import com.testmile.setu.requester.connector.SetuArg;
import com.testmile.setu.requester.connector.SetuResponse;
import com.testmile.setu.requester.guiauto.GuiAutoComponentFactory;
import com.testmile.setu.requester.guiauto.With;
import com.testmile.setu.requester.guiauto.component.Browser;
import com.testmile.setu.requester.guiauto.component.ChildWindow;
import com.testmile.setu.requester.guiauto.component.DomRoot;
import com.testmile.setu.requester.guiauto.component.DropDown;
import com.testmile.setu.requester.guiauto.component.Frame;
import com.testmile.setu.requester.guiauto.component.GuiElement;
import com.testmile.setu.requester.guiauto.component.GuiMultiElement;
import com.testmile.setu.requester.guiauto.component.MainWindow;
import com.testmile.setu.requester.guiauto.component.RadioGroup;
import com.testmile.setu.requester.guiauto.component.WebAlert;
import com.testmile.setu.requester.testsession.TestSession;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;

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
	public WebAlert webAlert() throws Exception {
		String elemSetuId = takeElementFindingAction(SetuActionType.GUIAUTO_CREATE_ALERT);
		return GuiAutoComponentFactory.WebAlert(this.testSession, this, elemSetuId);
	}
	
	@Override
	public ChildWindow childWindow(With... locators) throws Exception {
		return this.mainWindow.childWindow(locators);
	}

	@Override
	public Frame frame(With... locators) throws Exception {
		return this.domRoot().frame(locators);
	}

	@Override
	public MainWindow mainWindow() throws Exception {
		return this.mainWindow;
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
	
	protected void setMainWindow(MainWindow win) throws Exception {
		this.mainWindow = win;
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
	public void slowMotion(boolean on, int interval) throws Exception {
		this.sendRequest(
				SetuActionType.GUIAUTO_SET_SLOMO,
				SetuArg.arg("on", on),
				SetuArg.arg("interval", interval)
		);
	}
	
	@Override
	public void slowMotion(boolean on) throws Exception {
		this.sendRequest(
				SetuActionType.GUIAUTO_SET_SLOMO,
				SetuArg.arg("on", on)
		);
	}
	
	@Override
	public boolean isGui() {
		return false;
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
	public void executeJavaScript(String script) throws Exception {
		this.browser.executeJavaScript(script);
	}

}