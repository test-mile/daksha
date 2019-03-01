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

import org.testng.annotations.Test;

import com.testmile.daksha.core.guiauto.maker.selenium.SeleniumBuilder;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.daksha.tpi.testng.TestNGBaseTest;
import com.testmile.trishanku.tpi.enums.OSType;

public class MethodLevelSetup extends TestNGBaseTest {
	private ThreadLocal<SetuClientGuiAutomator> threadWiseAutomator = new ThreadLocal<SetuClientGuiAutomator>();
	
	protected void tweakCentralContext(TestContext centralContext) throws Exception {
		centralContext.setTargetPlatform(OSType.MAC);
	}
	
	protected void setUpMethod(TestContext testContext) throws Exception {
		// Create Selenium automator with context options
		SeleniumBuilder builder = new SeleniumBuilder(testContext);
		threadWiseAutomator.set(builder.build());
	}
	
	private void goToUrl(String url) throws Exception {
		SetuClientGuiAutomator automator = this.threadWiseAutomator.get();
		automator.goTo(url);
		System.out.println(automator.getPageTitle());		
	}
	
	@Test
	public void test1() throws Exception{
		goToUrl("https://www.google.com");
	}
	
	@Test
	public void test2() throws Exception{
		goToUrl("http://www.testmile.com");
	}
	
	protected void tearDownMethod(TestContext testContext) throws Exception {
		this.threadWiseAutomator.get().close();
	}
}
