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

package arjuna.ex.guiauto.models.v1.directautomator;

import com.testmile.arjuna.tpi.Arjuna;
import com.testmile.arjuna.tpi.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.guiauto.With;
import com.testmile.arjuna.tpi.guiauto.component.DropDown;
import com.testmile.arjuna.tpi.test.TestConfig;

public class DirectAutomator{
	
	public static void main(String[] args) throws Exception {
		// Initialize Daksha
		TestConfig config = Arjuna.init();
		
		// Create Automator (default is Selenium) with default options
		GuiAutomator automator = Arjuna.createGuiAutomator(config);	
		
		// Login
		automator.browser().goToUrl("http://192.168.56.103/wp-admin");
		automator.element(With.id("user_login")).setText("user");
		automator.element(With.id("user_pass")).setText("bitnami");
		automator.element(With.id("wp-submit")).click();
		automator.element(With.className("welcome-view-site")).waitUntilClickable();
		
		// Tweak Settings
		automator.element(With.linkText("Settings")).click();
		DropDown roleSelect = automator.dropdown(With.id("default_role"));
		System.out.println(roleSelect.hasVisibleTextSelected("Subscriber"));
		System.out.println(roleSelect.hasValueSelected("subscriber"));
		System.out.println(roleSelect.hasIndexSelected(2));
		System.out.println(roleSelect.getFirstSelectedOptionText());
		roleSelect.selectByValue("editor");
		roleSelect.selectByVisibleText("Subscriber");
		roleSelect.selectByIndex(4);
		
		// Logout
		automator.browser().goToUrl("http://192.168.56.103/wp-login.php?action=logout");
		automator.quit();	
	}
}