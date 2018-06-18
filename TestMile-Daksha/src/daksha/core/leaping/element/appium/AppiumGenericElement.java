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
package daksha.core.leaping.element.appium;

import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.selenium.DefaultSeleniumElementProxy;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.pageobject.Page;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumGenericElement extends DefaultSeleniumElementProxy<AppiumDriver<MobileElement>,MobileElement>{
	public AppiumGenericElement(Page page, GuiAutomator<AppiumDriver<MobileElement>,MobileElement> automator, GuiElementProxy eProxy) throws Exception{
		super(page, automator, eProxy);
	}

	public AppiumGenericElement(GuiAutomator<AppiumDriver<MobileElement>,MobileElement> automator, GuiElementProxy eProxy) throws Exception{
		super(automator, eProxy);
	}
	
}