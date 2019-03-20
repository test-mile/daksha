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

import com.testmile.arjuna.lib.setu.requester.config.TestConfig;
import com.testmile.arjuna.lib.setu.requester.guiauto.automator.DefaultGuiAutomator;
import com.testmile.arjuna.tpi.setu.requester.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.testng.TestNGBaseTest;

public class TestMile extends TestNGBaseTest {
	private ThreadLocal<GuiAutomator> threadWiseAutomator = new ThreadLocal<GuiAutomator>();
	
	protected void setUpClass(TestConfig testConfig) throws Exception {
		GuiAutomator automator = new DefaultGuiAutomator(testConfig);
		threadWiseAutomator.set(automator);
		automator.launch();
	}
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = this.threadWiseAutomator.get();
		automator.goToUrl("https://testmile.com");
		System.out.println(automator.mainWindow().getTitle());
	}
	
	public void tearDownClass(TestConfig testConfig) throws Exception {
		this.threadWiseAutomator.get().quit();
	}
}
