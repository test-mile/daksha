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
package daksha.tpi.leaping.pageobject;

import java.util.Map;

import daksha.core.leaping.actions.ElementCreationHandler;
import daksha.core.leaping.actions.automator.BrowserActionHandler;
import daksha.core.leaping.actions.automator.ImageComparator;
import daksha.core.leaping.actions.automator.NativeWindowActionHandler;
import daksha.core.leaping.automator.ManagedGuiAutomator;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.loader.PageDefLoader;

public interface Page extends ManagedGuiAutomator, ElementCreationHandler, 
ImageComparator, NativeWindowActionHandler, BrowserActionHandler{
	
	String getName();
	UiAutomationContext getContext() throws Exception;
	Page getParent() throws Exception;;
	
	String getLabel() throws Exception;

	GuiElementProxy dropdown(String name) throws Exception;
	GuiElementProxy element(String name) throws Exception;
	MultiGuiElementProxy elements(String name) throws Exception;
	GuiAutomator<?,?> getGuiAutomator() throws Exception;
}
