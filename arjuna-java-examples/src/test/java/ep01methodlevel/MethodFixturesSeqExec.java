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

package arjex.s05basicfixtures.ep01methodlevel;

import org.testng.annotations.Test;

import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.test.TestConfig;
import arjuna.tpi.testng.TestNGBaseTest;

public class MethodFixturesSeqExec extends TestNGBaseTest {
	private GuiAutomator automator = null;
	
	protected void setUpMethod(TestConfig testConfig) throws Exception {
		automator = Arjuna.createGuiAutomator();
	}
	
	protected void tearDownMethod(TestConfig testConfig) throws Exception {
		automator.quit();
		automator = null;
	}
	
	private void goToUrl(String url) throws Exception {
		automator.Browser().goToUrl(url);
		System.out.println(automator.MainWindow().getTitle());	
	}
	
	@Test
	public void test1() throws Exception{
		goToUrl("https://www.google.com");
	}
	
	@Test
	public void test2() throws Exception{
		goToUrl("http://www.testmile.com");
	}
}
