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

package daksha.ex.gettingstarted;

import daksha.Daksha;
import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.guiauto.enums.OSType;
import daksha.tpi.CentralTestContext;
import daksha.tpi.TestContext;
import daksha.tpi.enums.Browser;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.guiauto.maker.GuiAutomatorFactory;
import daksha.tpi.guiauto.maker.selenium.SeleniumBuilder;

public class Basic2WithDefaultTestContext{
	
	public static void main (String args[]) throws Exception {
		// Initialize and set central configuration
		CentralTestContext centralContext = Daksha.init();
		centralContext.setTargetPlatform(OSType.MAC);
		centralContext.freeze();
		
		// Get default test context. This has the same values as central context
		// The difference is that you can modify it.
		TestContext context = Daksha.getDefaultTestContext();
		context.setBrowserType(Browser.HTML_UNIT);
		context.getGuiAutoMaxWaitTime();
		
		// Create Selenium automator with context options
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(context);
		GuiAutomatorProxy automator = builder.build();

		automator.goTo("https://www.google.com");
		System.out.println(automator.getPageTitle());
		automator.close();
	}

}
