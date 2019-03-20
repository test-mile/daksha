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

package com.testmile.arjuna.lib.setu.requester.guiauto.automator;

import com.testmile.arjuna.lib.enums.GuiAutomationContext;
import com.testmile.arjuna.lib.setu.requester.config.TestConfig;
import com.testmile.arjuna.lib.setu.requester.connector.SetuManagedObject;
import com.testmile.arjuna.tpi.setu.requester.guiauto.With;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.Browser;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.ChildWindow;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.DomRoot;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.DropDown;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.Frame;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.GuiElement;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.GuiMultiElement;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.MainWindow;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.RadioGroup;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.WebAlert;

public interface AppAutomator extends SetuManagedObject{
	TestConfig getConfig();

	GuiElement element(With... locators) throws Exception;

	GuiMultiElement multiElement(With... locators) throws Exception;

	DropDown dropdown(With... locators) throws Exception;

	RadioGroup radioGroup(With... locators) throws Exception;
	
	ChildWindow childWindow(With... locators) throws Exception;
	
	Frame frame(With... locators) throws Exception;

	MainWindow mainWindow() throws Exception;
	
	ChildWindow latestChildWindow() throws Exception;
	
	void closeAllChildWindows() throws Exception;

	WebAlert webAlert() throws Exception;

	GuiAutomationContext getAutomationContext();

	DomRoot domRoot();
	
	Browser browser();
	
	void executeJavaScript(String script) throws Exception;

	void slowMotion(boolean on, int interval) throws Exception;

	void slowMotion(boolean on) throws Exception;

	boolean isGui();
}