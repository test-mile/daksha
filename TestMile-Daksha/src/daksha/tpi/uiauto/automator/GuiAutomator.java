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
package daksha.tpi.uiauto.automator;

import java.util.Map;

import daksha.core.batteries.config.TestContext;
import daksha.core.uiauto.actions.GuiElementCreationHandler;
import daksha.core.uiauto.actions.automator.BrowserActionHandler;
import daksha.core.uiauto.actions.automator.ImageComparison;
import daksha.core.uiauto.actions.automator.MobileActionHandler;
import daksha.core.uiauto.actions.automator.MouseActionHandler;
import daksha.core.uiauto.actions.automator.NativeWindowActionHandler;
import daksha.core.uiauto.automator.ManagedGuiAutomator;
import daksha.core.uiauto.element.proxy.GuiElementProxy;
import daksha.core.uiauto.element.proxy.GuiMultiElementProxy;
import daksha.core.uiauto.enums.GuiElementLoaderType;
import daksha.core.uiauto.enums.OSType;
import daksha.core.uiauto.identifier.GuiElementMetaData;
import daksha.tpi.uiauto.enums.GuiAutomationContext;

public interface GuiAutomator extends ManagedGuiAutomator, NativeWindowActionHandler, GuiElementCreationHandler, BrowserActionHandler, ImageComparison, MouseActionHandler, MobileActionHandler{

	void setAutomatorContext(GuiAutomationContext screen);
	
	GuiElementLoaderType getElementLoaderType() throws Exception;
	
	void setElementLoaderType(GuiElementLoaderType loaderType) throws Exception;

	void load() throws Exception;

	void setCapabilities(Map<String, ?> caps);

	void init() throws Exception;

	String getName();

	void setWaitTime(int waitTime);

	void setOSType(OSType platformType);

	TestContext getTestContext();

	int getWaitTime();
	
	GuiElementProxy element(GuiElementMetaData eData) throws Exception;
	
	GuiMultiElementProxy elements(GuiElementMetaData eData) throws Exception;

}
