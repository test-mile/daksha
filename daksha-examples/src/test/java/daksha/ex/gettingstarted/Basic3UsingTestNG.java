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

import com.testmile.daksha.Daksha;
import com.testmile.daksha.tpi.test.DakshaTestConfig;
import com.testmile.daksha.tpi.testng.TestNGBaseTest;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;

public class Basic3UsingTestNG extends TestNGBaseTest {
	private ThreadLocal<GuiAutomator> threadWiseAutomator = new ThreadLocal<GuiAutomator>();
	
	protected void setUpClass(DakshaTestConfig config) throws Exception {
		threadWiseAutomator.set(Daksha.createGuiAutomator(config));
		System.out.println("here" + this.threadWiseAutomator.get());
	}
	
	@Test
	public void test() throws Exception{
		GuiAutomator automator = this.threadWiseAutomator.get();
		automator.launch();
		automator.browser().goToUrl("https://www.google.com");
		System.out.println(automator.mainWindow().getTitle());
	}
	
	protected void tearDownClass(DakshaTestConfig testConfig) throws Exception {
		this.threadWiseAutomator.get().quit();
	}
}
