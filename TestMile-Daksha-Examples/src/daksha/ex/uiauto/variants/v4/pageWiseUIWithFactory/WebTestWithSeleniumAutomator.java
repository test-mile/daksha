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

package daksha.ex.uiauto.variants.v4.pageWiseUIWithFactory;

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
	private ThreadLocal<Gui> threadWiseHomePage = new ThreadLocal<Gui>();
	
	protected void setCentralOptions() throws Exception {
		Daksha.setOSType(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testContext) throws Exception {
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(Daksha.getTestContext(this.getTestContextName()));
		Gui home = GuiFactory.createGui(builder.build(), "wordpress/Home.gns");
		threadWiseHomePage.set(home);
	}
	
	@Test
	public void test() throws Exception{
		Gui gui =  this.threadWiseHomePage.get();

		gui.goTo(AppConfig.WP_ADMIN_URL);	

		GuiElement userTextBox = gui.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(AppConfig.USER_NAME);
		gui.element("PASSWORD").enterText(AppConfig.PASSWORD);
		gui.element("SUBMIT").click();		
		gui.waitForBody();
		
		gui = GuiFactory.createGui(gui.getAutomator(), "wordpress/LeftNavigation.gns");
		gui.element("POSTS").hover();
		gui.element("CATEGORIES").click();	
		gui.waitForBody();
		
		gui = GuiFactory.createGui(gui.getAutomator(), "wordpress/Categories.gns");
		GuiMultiElement tags = gui.elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (GuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}

		gui = GuiFactory.createGui(gui.getAutomator(), "wordpress/LeftNavigation.gns");
		gui.element("SETTINGS").click();
		
		gui = GuiFactory.createGui(gui.getAutomator(), "wordpress/Settings.gns");
		GuiElement blogNameTextBox = gui.element("BLOG_NAME");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		gui.element("MEMBERSHIP").check();

		// Experiments with Select control - Selection using different attributes
		GuiElement roleDropDown = gui.element("ROLE").asDropDown();
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");

		gui.goTo(AppConfig.WP_LOGOUT_URL);
	}
	
	@AfterClass
	public void closeAutomator(ITestContext testContext) throws Exception {
		this.threadWiseHomePage.get().close();
	}
}
