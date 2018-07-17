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
package daksha.core.guiauto.element.appium;

import daksha.core.guiauto.automator.ConcreteGuiAutomator;
import daksha.core.guiauto.element.proxy.GuiElementProxy;
import daksha.core.guiauto.element.selenium.AbstractSeleniumConcreteElement;
import daksha.tpi.guiauto.gui.Gui;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumGenericElement extends AbstractSeleniumConcreteElement<AppiumDriver<MobileElement>,MobileElement>{
	public AppiumGenericElement(Gui gui, ConcreteGuiAutomator<AppiumDriver<MobileElement>,MobileElement> automator, GuiElementProxy eProxy) throws Exception{
		super(gui, automator, eProxy);
	}

	public AppiumGenericElement(ConcreteGuiAutomator<AppiumDriver<MobileElement>,MobileElement> automator, GuiElementProxy eProxy) throws Exception{
		super(automator, eProxy);
	}
	
}
