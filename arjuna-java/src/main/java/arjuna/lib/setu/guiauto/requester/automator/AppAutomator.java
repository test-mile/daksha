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

package arjuna.lib.setu.guiauto.requester.automator;

import arjuna.lib.enums.GuiAutomationContext;
import arjuna.lib.setu.core.requester.connector.SetuManagedObject;
import arjuna.tpi.guiauto.With;
import arjuna.tpi.guiauto.component.Alert;
import arjuna.tpi.guiauto.component.Browser;
import arjuna.tpi.guiauto.component.ChildWindow;
import arjuna.tpi.guiauto.component.DomRoot;
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.Frame;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.component.GuiMultiElement;
import arjuna.tpi.guiauto.component.MainWindow;
import arjuna.tpi.guiauto.component.RadioGroup;
import arjuna.tpi.test.TestConfig;

public interface AppAutomator extends SetuManagedObject{
	TestConfig getConfig();

	GuiElement Element(With... locators) throws Exception;

	GuiMultiElement MultiElement(With... locators) throws Exception;

	DropDown DropDown(With... locators) throws Exception;

	RadioGroup RadioGroup(With... locators) throws Exception;
	
	ChildWindow childWindow(With... locators) throws Exception;
	
	Frame Frame(With... locators) throws Exception;

	MainWindow MainWindow() throws Exception;
	
	ChildWindow LatestChildWindow() throws Exception;
	
	void closeAllChildWindows() throws Exception;

	Alert Alert() throws Exception;

	GuiAutomationContext getAutomationContext();

	DomRoot DomRoot();
	
	Browser Browser();
	
	void executeJavaScript(String script) throws Exception;

	void enableSlowMotion(boolean on, int interval) throws Exception;

	void enableSlowMotion(boolean on) throws Exception;

	boolean isGui();
}