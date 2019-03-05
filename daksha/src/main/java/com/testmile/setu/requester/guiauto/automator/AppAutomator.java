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
import com.testmile.setu.requester.SetuManagedObject;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;

public interface AppAutomator extends SetuManagedObject{
	TestConfig getConfig();

	GuiElement element(With with, String value) throws Exception;

	GuiMultiElement multiElement(With with, String value) throws Exception;

	DropDown dropdown(With with, String value) throws Exception;

	RadioGroup radioGroup(With with, String value) throws Exception;

	Frame frame(With with, String value) throws Exception;

	MainWindow mainWindow() throws Exception;

	Alert alert() throws Exception;

	GuiAutomationContext getAutomationContext();

	DomRoot domRoot();

}