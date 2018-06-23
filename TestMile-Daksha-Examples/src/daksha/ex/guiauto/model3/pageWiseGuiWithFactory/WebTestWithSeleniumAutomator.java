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

package daksha.ex.guiauto.model3.pageWiseGuiWithFactory;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.Daksha;
import daksha.core.cleanup.enums.OSType;
import daksha.ex.config.AppConfig;
import daksha.tpi.cleanup.constructor.GuiAutomatorFactory;
import daksha.tpi.cleanup.constructor.GuiFactory;
import daksha.tpi.cleanup.constructor.selenium.SeleniumBuilder;
import daksha.tpi.cleanup.element.GuiElement;
import daksha.tpi.cleanup.element.MultiGuiElement;
import daksha.tpi.cleanup.gui.Gui;
import daksha.tpi.testng.TestNGBaseTest;

public class WebTestWithSeleniumAutomator extends TestNGBaseTest{
	private ThreadLocal<Gui> threadWiseHomePage = new ThreadLocal<Gui>();
	
	protected void setCentralOptions() throws Exception {
		Daksha.setOSType(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testContext) throws Exception {
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(Daksha.getTestContext(this.getTestContextName()));
		Gui home = GuiFactory.createGui(builder.build(), "wordpress/HomePage.ini");
		threadWiseHomePage.set(home);
	}
	
	@Test
	public void test() throws Exception{
		Gui gui = this.threadWiseHomePage.get();

		page.goTo(AppConfig.WP_ADMIN_URL);	

		GuiElement userTextBox = page.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(AppConfig.USER_NAME);
		page.element("PASSWORD").enterText(AppConfig.PASSWORD);
		page.element("SUBMIT").click();		
		page.waitForBody();
		
		page = GuiFactory.createGui(page.getGuiAutomator(), "wordpress/LeftNavigation.ini");
		page.element("POSTS").hover();
		page.element("CATEGORIES").click();	
		page.waitForBody();
		
		page = GuiFactory.createGui(page.getGuiAutomator(), "wordpress/Categories.ini");
		MultiGuiElement tags = page.elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (GuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}

		page = GuiFactory.createGui(page.getGuiAutomator(), "wordpress/LeftNavigation.ini");
		page.element("SETTINGS").click();
		
		page = GuiFactory.createGui(page.getGuiAutomator(), "wordpress/Settings.ini");
		GuiElement blogNameTextBox = page.element("BLOG_NAME");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		page.element("MEMBERSHIP").check();

		// Experiments with Select control - Selection using different attributes
		GuiElement roleDropDown = page.element("ROLE").asDropDown();
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");

		page.goTo(AppConfig.WP_LOGOUT_URL);
	}
	
	@AfterClass
	public void closeAutomator(ITestContext testContext) throws Exception {
		this.threadWiseHomePage.get().close();
	}
}
