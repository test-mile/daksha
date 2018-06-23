/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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
package daksha.tpi.cleanup.gui;

import daksha.core.cleanup.actions.GuiElementCreationHandler;
import daksha.core.cleanup.actions.automator.BrowserActionHandler;
import daksha.core.cleanup.actions.automator.ImageComparison;
import daksha.core.cleanup.actions.automator.MobileActionHandler;
import daksha.core.cleanup.actions.automator.MouseActionHandler;
import daksha.core.cleanup.actions.automator.NativeWindowActionHandler;
import daksha.core.cleanup.automator.ManagedGuiAutomator;
import daksha.core.cleanup.automator.proxy.GuiAutomatorProxy;
import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.core.cleanup.element.proxy.MultiGuiElementProxy;
import daksha.core.cleanup.loader.GuiDefinition;
import daksha.tpi.cleanup.enums.GuiAutomationContext;

public interface Gui extends ManagedGuiAutomator, GuiElementCreationHandler, 
ImageComparison, NativeWindowActionHandler, BrowserActionHandler, MouseActionHandler, MobileActionHandler{
	
	String getLabel() throws Exception;
	String getName();
	
	GuiDefinition getGuiDef() throws Exception;
	Gui getParent() throws Exception;
	
	GuiAutomatorProxy getGuiAutomator() throws Exception; 
	GuiAutomationContext getAutomatorContext() throws Exception;

	GuiElementProxy element(String name) throws Exception;
	MultiGuiElementProxy elements(String name) throws Exception;
	
	void addChild(String label, String defFilePath) throws Exception;
	
	Gui gui(String string) throws Exception;

}
