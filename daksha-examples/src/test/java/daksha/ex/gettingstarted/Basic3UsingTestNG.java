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

import org.testng.annotations.Test;

import com.testmile.daksha.core.guiauto.automator.DefaultGuiAutomator;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.testng.TestNGBaseTest;

public class Basic3UsingTestNG extends TestNGBaseTest {
	private ThreadLocal<GuiAutomator> threadWiseAutomator = new ThreadLocal<GuiAutomator>();
	
	protected void setUpClass(TestConfig testConfig) throws Exception {
		threadWiseAutomator.set(new DefaultGuiAutomator(testConfig));
	}
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = this.threadWiseAutomator.get();
		automator.goToUrl("https://www.google.com");
		System.out.println(automator.mainWindow().getTitle());
	}
	
	protected void tearDownClass(TestConfig testConfig) throws Exception {
		this.threadWiseAutomator.get().quit();
	}
}
