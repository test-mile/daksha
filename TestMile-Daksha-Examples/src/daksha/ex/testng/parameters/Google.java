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

package daksha.ex.testng.parameters;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.Daksha;
import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.guiauto.enums.OSType;
import daksha.tpi.TestContext;
import daksha.tpi.enums.Browser;
import daksha.tpi.guiauto.maker.GuiAutomatorFactory;
import daksha.tpi.guiauto.maker.selenium.SeleniumBuilder;
import daksha.tpi.testng.TestNGBaseTest;

public class Google extends TestNGBaseTest {
	private ThreadLocal<GuiAutomatorProxy> threadWiseAutomator = new ThreadLocal<GuiAutomatorProxy>();
	
	protected void setCentralOptions() throws Exception {
		Daksha.setOSType(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator() throws Exception {
		// Get test context
		TestContext context = this.getContext();
		
		// Create Selenium automator with context options
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(context);
		threadWiseAutomator.set(builder.build());
	}
	
	@Test
	public void test() throws Exception{
		GuiAutomatorProxy automator = this.threadWiseAutomator.get();
		automator.goTo("https://www.google.com");
		System.out.println(automator.getPageTitle());
		System.out.println(this.getContext().getTestRunEnvName());
		automator.takeScreenshot();
	}
	
	@AfterClass
	public void closeAutomator() throws Exception {
		this.threadWiseAutomator.get().close();
	}
}
