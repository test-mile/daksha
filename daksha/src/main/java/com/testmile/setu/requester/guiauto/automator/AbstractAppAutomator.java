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

import com.testmile.daksha.tpi.guiauto.Alert;
import com.testmile.daksha.tpi.guiauto.DomRoot;
import com.testmile.daksha.tpi.guiauto.DropDown;
import com.testmile.daksha.tpi.guiauto.Frame;
import com.testmile.daksha.tpi.guiauto.GuiElement;
import com.testmile.daksha.tpi.guiauto.GuiMultiElement;
import com.testmile.daksha.tpi.guiauto.MainWindow;
import com.testmile.daksha.tpi.guiauto.RadioGroup;
import com.testmile.daksha.tpi.guiauto.With;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.test.TestSession;
import com.testmile.setu.requester.BaseSetuObject;
import com.testmile.setu.requester.SetuActionType;
import com.testmile.setu.requester.SetuArg;
import com.testmile.setu.requester.SetuResponse;
import com.testmile.setu.requester.guiauto.GuiAutoElementFactory;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;

public class AbstractAppAutomator extends BaseSetuObject implements AppAutomator {
	private DomRoot domRoot;
	private MainWindow mainWindow;
	private GuiAutomationContext autoContext;
	private TestConfig config;
	private TestSession testSession;
	
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

	private String createGenericElement(SetuActionType actionType, With with, String value) throws Exception {
		return this.takeElementFindingAction(
				actionType,
				SetuArg.with(with, value)
		);	
	}

	@Override
	public GuiElement element(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_ELEMENT, with, value);
		return GuiAutoElementFactory.createGuiElement(this.testSession, this, elemSetuId);
	}

	@Override
	public GuiMultiElement multiElement(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_MULTIELEMENT, with, value);
		return GuiAutoElementFactory.createGuiMultiElement(this.testSession, this, elemSetuId);
	}

	@Override
	public DropDown dropdown(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_DROPDOWN, with, value);
		return GuiAutoElementFactory.createGuiDropDown(this.testSession, this, elemSetuId);
	}

	@Override
	public RadioGroup radioGroup(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_RADIOGROUP, with, value);
		return GuiAutoElementFactory.createRadioGroup(this.testSession, this, elemSetuId);
	}

	@Override
	public Frame frame(With with, String value) throws Exception {
		String elemSetuId = createGenericElement(SetuActionType.GUIAUTO_CREATE_FRAME, with, value);
		return GuiAutoElementFactory.createFrame(this.testSession, this, elemSetuId);
	}

	@Override
	public Alert alert() throws Exception {
		String elemSetuId = takeElementFindingAction(SetuActionType.GUIAUTO_CREATE_ALERT);
		return GuiAutoElementFactory.createAlert(this.testSession, this, elemSetuId);
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
	
	public void setMainWindow(MainWindow win) throws Exception {
		this.mainWindow = win;
	}
	
	public TestSession getTestSession() {
		return this.testSession;
	}

}