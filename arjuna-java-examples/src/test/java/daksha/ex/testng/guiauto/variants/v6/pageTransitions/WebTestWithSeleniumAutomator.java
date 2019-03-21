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

package daksha.ex.testng.guiauto.variants.v6.pageTransitions;

import org.testng.annotations.Test;

import com.testmile.arjuna.lib.core.config.DefaultTestContext;
import com.testmile.arjuna.lib.enums.OSType;
import com.testmile.arjuna.tpi.testng.TestNGBaseTest;
import com.testmile.setu.actor.guiauto.core.core.builder.SeleniumBuilder;

public class WebTestWithSeleniumAutomator extends TestNGBaseTest{
	private ThreadLocal<Home> threadWiseHome = new ThreadLocal<Home>();
	
	protected void tweakCentralContext(DefaultTestContext centralContext)  throws Exception {
		centralContext.setTargetPlatform(OSType.MAC);
	}
	
	public void setUpClass(DefaultTestContext testContext) throws Exception {
		SeleniumBuilder builder = new SeleniumBuilder(testContext);
		Home home = new Home(builder.build());
		threadWiseHome.set(home);
	}
	
	@Test
	public void test() throws Exception{
		Home home = this.threadWiseHome.get();
		Dashboard db = home.login();
		Categories cat = db.goToCategries();
		cat.playWithCats();
		Settings settings = cat.goToSettings();
		settings.playWithSettings();
		settings.logout();
	}
	
	public void tearDownClass(DefaultTestContext testContext) throws Exception {
		this.threadWiseHome.get().close();
	}
}
