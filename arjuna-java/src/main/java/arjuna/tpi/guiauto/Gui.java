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

package arjuna.tpi.guiauto;

import arjuna.lib.setu.guiauto.requester.automator.AppAutomator;
import arjuna.tpi.guiauto.component.ChildWindow;
import arjuna.tpi.guiauto.component.DropDown;
import arjuna.tpi.guiauto.component.Frame;
import arjuna.tpi.guiauto.component.GuiElement;
import arjuna.tpi.guiauto.component.GuiMultiElement;
import arjuna.tpi.guiauto.component.RadioGroup;

public interface Gui extends AppAutomator{

	void addChild(String label, Gui gui);

	GuiElement Element(String name) throws Exception;

	GuiMultiElement MultiElement(String name) throws Exception;
	
	DropDown DropDown(String name) throws Exception;

	RadioGroup RadioGroup(String name) throws Exception;
	
	ChildWindow ChildWindow(String name) throws Exception;
	
	Frame Frame(String name) throws Exception;

	GuiAutomator getAutomator();

}
