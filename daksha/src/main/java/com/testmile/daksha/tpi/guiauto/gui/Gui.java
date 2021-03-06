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
package com.testmile.daksha.tpi.guiauto.gui;

import com.testmile.daksha.core.guiauto.actions.GuiElementCreationHandler;
import com.testmile.daksha.core.guiauto.actions.automator.BrowserActionHandler;
import com.testmile.daksha.core.guiauto.actions.automator.ImageComparison;
import com.testmile.daksha.core.guiauto.actions.automator.MobileActionHandler;
import com.testmile.daksha.core.guiauto.actions.automator.MouseActionHandler;
import com.testmile.daksha.core.guiauto.actions.automator.NativeWindowActionHandler;
import com.testmile.daksha.core.guiauto.automator.ManagedGuiAutomator;
import com.testmile.daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import com.testmile.daksha.core.guiauto.element.proxy.GuiElementProxy;
import com.testmile.daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import com.testmile.daksha.core.guiauto.namestore.GuiNamespace;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;

public interface Gui extends ManagedGuiAutomator, GuiElementCreationHandler, 
ImageComparison, NativeWindowActionHandler, BrowserActionHandler, MouseActionHandler, MobileActionHandler{
	
	String getLabel() throws Exception;
	String getName();
	
	GuiNamespace getDefinition() throws Exception;
	Gui getParent() throws Exception;
	
	GuiAutomatorProxy getAutomator() throws Exception; 
	GuiAutomationContext getAutomatorContext() throws Exception;

	GuiElementProxy element(String name) throws Exception;
	GuiMultiElementProxy elements(String name) throws Exception;
	
	void addChild(String label, String defFilePath) throws Exception;
	
	Gui gui(String string) throws Exception;

}
