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

package daksha.ex.guiauto.gettingstarted;

import com.testmile.daksha.Daksha;
import com.testmile.daksha.tpi.test.DakshaTestConfig;
import com.testmile.setu.requester.guiauto.GuiDriverExtendedConfig;
import com.testmile.setu.requester.guiauto.automator.GuiAutomator;

public class Basic3WithExtendedDriverConfig{
	
	public static void main (String args[]) throws Exception {
		// Initialize Daksha
		DakshaTestConfig config = Daksha.init();
		
		GuiDriverExtendedConfig exConfig = new GuiDriverExtendedConfig();
		exConfig.browserArg("--headless");

		// Create Automator (default is Selenium) with context config
		GuiAutomator automator = Daksha.createGuiAutomator(config, exConfig);
		automator.launch();

		// Basic flow in Firefox, as per the context config
		automator.browser().goToUrl("https://www.google.com");
		System.out.println(automator.mainWindow().getTitle());
		automator.quit();
	}

}