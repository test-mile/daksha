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

package arjuna.ex.guiauto.models.v2.simpleapp;

import com.testmile.arjuna.Arjuna;
import com.testmile.arjuna.lib.setu.requester.guiauto.gui.DefaultGui;
import com.testmile.arjuna.tpi.setu.requester.guiauto.Gui;
import com.testmile.arjuna.tpi.setu.requester.guiauto.GuiAutomator;
import com.testmile.arjuna.tpi.setu.requester.guiauto.component.DropDown;
import com.testmile.arjuna.tpi.test.DakshaTestConfig;

public class SimpleApp{
	
	public static void main(String[] args) throws Exception {
		// Initialize Daksha
		DakshaTestConfig config = Arjuna.init();
		
		// Create Automator (default is Selenium) with default options
		GuiAutomator automator = Arjuna.createGuiAutomator(config);
		
		// Create Gui. Provide GNS file path.
		Gui app = new DefaultGui("WordPress", automator, "simpleapp/WordPress.gns");

		// Login
		app.browser().goToUrl("http://192.168.56.103/wp-admin");
		app.element("login").setText("user");
		app.element("password").setText("bitnami");
		app.element("submit").click();
		app.element("view-site").waitUntilClickable();
		
		// Tweak Settings
		app.element("settings").click();
		
		DropDown roleSelect = app.dropdown("role");
		System.out.println(roleSelect.hasVisibleTextSelected("Subscriber"));
		System.out.println(roleSelect.hasValueSelected("subscriber"));
		System.out.println(roleSelect.hasIndexSelected(2));
		System.out.println(roleSelect.getFirstSelectedOptionText());
		roleSelect.selectByValue("editor");
		roleSelect.selectByVisibleText("Subscriber");
		roleSelect.selectByIndex(4);
		
		// Logout
		app.browser().goToUrl("http://192.168.56.103/wp-login.php?action=logout");		
		app.automator().quit();
	}
}
