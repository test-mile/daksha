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
package com.testmile.daksha.tpi.guiauto.maker;

import com.testmile.daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import com.testmile.daksha.core.guiauto.automator.sikuli.SikuliGuiDriver;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.maker.appium.AppiumBuilder;
import com.testmile.daksha.tpi.guiauto.maker.selenium.SeleniumBuilder;

public class GuiAutomatorFactory {
	
	public static AppiumBuilder getAppiumBuilder(TestContext testContext) throws Exception{
		return new AppiumBuilder(testContext);
	}
	
	public static SeleniumBuilder getSeleniumBuilder(TestContext testContext) throws Exception{
		return new SeleniumBuilder(testContext);
	}
	
	public static GuiAutomatorProxy getSikuli(TestContext testContext) throws Exception{
		return new GuiAutomatorProxy(new SikuliGuiDriver(testContext));
	}
	
}
