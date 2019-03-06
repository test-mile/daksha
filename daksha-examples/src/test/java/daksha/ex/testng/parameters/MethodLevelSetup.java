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

import com.testmile.daksha.tpi.testng.TestNGBaseTest;
import com.testmile.setu.requester.config.TestConfig;
import com.testmile.setu.requester.guiauto.automator.DefaultGuiAutomator;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;

public class MethodLevelSetup extends TestNGBaseTest {
	private ThreadLocal<GuiAutomator> threadWiseAutomator = new ThreadLocal<GuiAutomator>();
	
	protected void setUpMethod(TestConfig testConfig) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator(testConfig);
		threadWiseAutomator.set(automator);
		automator.launch();
	}
	
	private void goToUrl(String url) throws Exception {
		GuiAutomator automator = this.threadWiseAutomator.get();
		automator.goToUrl(url);
		System.out.println(automator.mainWindow().getTitle());	
	}
	
	@Test
	public void test1() throws Exception{
		goToUrl("https://www.google.com");
	}
	
	@Test
	public void test2() throws Exception{
		goToUrl("http://www.testmile.com");
	}
	
	protected void tearDownMethod(TestConfig testConfig) throws Exception {
		this.threadWiseAutomator.get().quit();
	}
}
