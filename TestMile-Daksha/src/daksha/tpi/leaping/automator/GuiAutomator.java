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
package daksha.tpi.leaping.automator;

import java.util.Map;

import daksha.core.batteries.config.TestContext;
import daksha.core.leaping.actions.GuiElementCreationHandler;
import daksha.core.leaping.actions.automator.BrowserActionHandler;
import daksha.core.leaping.actions.automator.ImageComparator;
import daksha.core.leaping.actions.automator.MouseActionHandler;
import daksha.core.leaping.actions.automator.NativeWindowActionHandler;
import daksha.core.leaping.automator.ManagedGuiAutomator;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.OSType;
import daksha.core.leaping.identifier.GuiElementIdentifier;
import daksha.tpi.leaping.enums.GuiAutomationContext;

public interface GuiAutomator<D,E> extends ManagedGuiAutomator<D>, NativeWindowActionHandler, GuiElementCreationHandler, BrowserActionHandler, ImageComparator, MouseActionHandler{

	void setAutomatorContext(GuiAutomationContext screen);

	void switchToWebContext() throws Exception;
	
	void switchToNativeContext() throws Exception;
	
	ElementLoaderType getElementLoaderType() throws Exception;
	
	void setElementLoaderType(ElementLoaderType loaderType) throws Exception;

	void load() throws Exception;

	void setCapabilities(Map<String, ?> caps);

	void init() throws Exception;

	String getName();

	GuiElementIdentifier<D,E> getIdentifier();

	void setWaitTime(int waitTime);

	void setOSType(OSType platformType);

	TestContext getTestContext();

	int getWaitTime();

}