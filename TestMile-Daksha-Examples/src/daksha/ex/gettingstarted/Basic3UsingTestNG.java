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

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.guiauto.enums.OSType;
import daksha.tpi.TestContext;
import daksha.tpi.enums.Browser;
import daksha.tpi.guiauto.maker.GuiAutomatorFactory;
import daksha.tpi.guiauto.maker.selenium.SeleniumBuilder;
import daksha.tpi.testng.TestNGBaseTest;

public class Basic3UsingTestNG extends TestNGBaseTest {
	private ThreadLocal<GuiAutomatorProxy> threadWiseAutomator = new ThreadLocal<GuiAutomatorProxy>();
	
	@Override
	protected void tweakCentralContext(TestContext centralContext) throws Exception {
		centralContext.setTargetPlatform(OSType.MAC);
	}

	@Override
	protected void tweakTestContext(TestContext testContext) throws Exception {
		testContext.setBrowserType(Browser.HTML_UNIT);
	}
	
	@BeforeClass
	public void createAutomator() throws Exception {
		// Create Selenium automator with context options
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(this.getContext());
		threadWiseAutomator.set(builder.build());
	}
	
	@Test
	public void test() throws Exception{
		GuiAutomatorProxy automator = this.threadWiseAutomator.get();
		automator.goTo("https://www.google.com");
		System.out.println(automator.getPageTitle());
	}
	
	@AfterClass
	public void closeAutomator() throws Exception {
		this.threadWiseAutomator.get().close();
	}
}
