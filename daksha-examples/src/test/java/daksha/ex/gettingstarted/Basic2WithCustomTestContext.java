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

import com.testmile.daksha.Daksha;
import com.testmile.daksha.core.guiauto.automator.DefaultGuiAutomator;
import com.testmile.daksha.tpi.guiauto.GuiAutomator;
import com.testmile.daksha.tpi.test.TestConfig;
import com.testmile.daksha.tpi.test.TestContext;
import com.testmile.trishanku.Trishanku;
import com.testmile.trishanku.tpi.enums.Browser;

public class Basic2WithCustomTestContext{
	
	public static void main (String args[]) throws Exception {
		// Initialize Daksha
		Daksha.init();
		
		// Create a custom test context. Contains all values from central config
		// A context assists you in creating a new configuration programmatically.
		String contextName = "custom";
		TestContext context = Daksha.createTestContext(contextName);
		
		// Changing an option in context config as different from central option
		// You can tweak any number of options
		context.browserType(Browser.FIREFOX);
		
		// The build step sends information to Setu and creates a unique frozen config
		TestConfig config = context.build(); 

		// Create Automator (default is Selenium) with context config
		GuiAutomator automator = new DefaultGuiAutomator(config);
		automator.launch();

		// Basic flow in Firefox, as per the context config
		automator.goToUrl("https://www.google.com");
		System.out.println(automator.mainWindow().getTitle());
		automator.quit();
	}

}
