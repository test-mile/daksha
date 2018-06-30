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

package daksha.ex.uiauto.variants.v5.appAsCompositeUI;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.Daksha;
import daksha.core.uiauto.enums.OSType;
import daksha.ex.config.AppConfig;
import daksha.tpi.testng.TestNGBaseTest;
import daksha.tpi.uiauto.element.GuiElement;
import daksha.tpi.uiauto.element.GuiMultiElement;
import daksha.tpi.uiauto.gui.Gui;
import daksha.tpi.uiauto.maker.GuiAutomatorFactory;
import daksha.tpi.uiauto.maker.GuiFactory;
import daksha.tpi.uiauto.maker.selenium.SeleniumBuilder;

public class WebTestWithSeleniumAutomator extends TestNGBaseTest{
	private ThreadLocal<Gui> threadWiseApp = new ThreadLocal<Gui>();
	
	protected void setCentralOptions() throws Exception {
		Daksha.setOSType(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testContext) throws Exception {
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(Daksha.getTestContext(this.getTestContextName()));
		Gui app = GuiFactory.createAppFromDir("WordPress", builder.build(), "appwithpages");
		threadWiseApp.set(app);
	}
	
	@Test
	public void test() throws Exception{
		Gui app = this.threadWiseApp.get();

		app.goTo(AppConfig.WP_ADMIN_URL);	

		GuiElement userTextBox = app.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(AppConfig.USER_NAME);
		app.element("PASSWORD").enterText(AppConfig.PASSWORD);
		app.element("SUBMIT").click();		
		app.waitForBody();
		
		app.gui("LeftNavigation").element("POSTS").hover();
		app.gui("LeftNavigation").element("CATEGORIES").click();	
		app.waitForBody();
		
		GuiMultiElement tags = app.gui("Categories").elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (GuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}

		app.gui("LeftNavigation").element("SETTINGS").click();

		GuiElement blogNameTextBox = app.gui("Settings").element("BLOG_NAME");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		app.gui("Settings").element("MEMBERSHIP").check();

		// Experiments with Select control - Selection using different attributes
		GuiElement roleDropDown = app.gui("Settings").element("ROLE").asDropDown();
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");

		app.goTo(AppConfig.WP_LOGOUT_URL);
	}
	
	@AfterClass
	public void closeAutomator(ITestContext testContext) throws Exception {
		this.threadWiseApp.get().close();
	}
}
