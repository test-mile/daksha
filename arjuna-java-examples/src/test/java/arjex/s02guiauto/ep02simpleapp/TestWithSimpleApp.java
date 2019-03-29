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

package arjex.s02guiauto.ep02simpleapp;

import org.testng.annotations.Test;

import arjuna.tpi.Arjuna;
import arjuna.tpi.guiauto.DefaultGui;
import arjuna.tpi.guiauto.Gui;
import arjuna.tpi.guiauto.GuiAutomator;
import arjuna.tpi.guiauto.component.DropDown;

public class TestWithSimpleApp{
	
	@Test
	public void test() throws Exception{
		Arjuna.init();
		GuiAutomator automator = Arjuna.createGuiAutomator();
		
		// Create Gui. Provide GNS file path.
		Gui app = new DefaultGui(automator, "WordPress", "simpleapp/WordPress.gns");

		// Login
		app.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.login.url").asString());
		app.Element("login").setText("user");
		app.Element("password").setText("bitnami");
		app.Element("submit").click();
		app.Element("view-site").waitUntilClickable();
		
		// Tweak Settings
		app.Element("settings").click();
		
		DropDown roleSelect = app.DropDown("role");
		System.out.println(roleSelect.hasVisibleTextSelected("Subscriber"));
		System.out.println(roleSelect.hasValueSelected("subscriber"));
		System.out.println(roleSelect.hasIndexSelected(2));
		System.out.println(roleSelect.getFirstSelectedOptionText());
		roleSelect.selectByValue("editor");
		roleSelect.selectByVisibleText("Subscriber");
		roleSelect.selectByIndex(4);
		
		// Logout
		app.Browser().goToUrl(automator.getConfig().getUserOptionValue("wp.logout.url").asString());		
		app.getAutomator().quit();
	}
}
