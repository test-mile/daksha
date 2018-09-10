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
package daksha.tpi.guiauto.automator;

import java.util.Map;

import daksha.core.guiauto.actions.GuiElementCreationHandler;
import daksha.core.guiauto.actions.automator.BrowserActionHandler;
import daksha.core.guiauto.actions.automator.ImageComparison;
import daksha.core.guiauto.actions.automator.MobileActionHandler;
import daksha.core.guiauto.actions.automator.MouseActionHandler;
import daksha.core.guiauto.actions.automator.NativeWindowActionHandler;
import daksha.core.guiauto.automator.ManagedGuiAutomator;
import daksha.core.guiauto.element.proxy.GuiElementProxy;
import daksha.core.guiauto.element.proxy.GuiMultiElementProxy;
import daksha.core.guiauto.enums.GuiElementLoaderType;
import daksha.core.guiauto.enums.OSType;
import daksha.core.guiauto.identifier.GuiElementMetaData;
import daksha.tpi.TestContext;
import daksha.tpi.guiauto.enums.GuiAutomationContext;

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
