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

package arjex.s05basicfixtures.ep03testlevel;

import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.testng.TestNGBaseTest;

public class TestFixturesSeqExec extends TestNGBaseTest {
	
	protected void setUpTest(TestConfig testConfig) throws Exception {
		GuiAutomator automator = Arjuna.createGuiAutomator();
		Global.INSTANCE.setAutomator(automator);
	}
	
	protected void tearDownTest(TestConfig testConfig) throws Exception {
		getAutomator().quit();
		Global.INSTANCE.destroyAutomator();
	}
	
	protected GuiAutomator getAutomator() {
		return Global.INSTANCE.getAutomator();
	}
	
	protected void goToUrl(String url) throws Exception {
		getAutomator().Browser().goToUrl(url);
		System.out.println(getAutomator().MainWindow().getTitle());	
	}
}
