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

package daksha.ex.guiauto.model5.pageobjects.simple;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.Daksha;
import daksha.core.leaping.enums.OSType;
import daksha.ex.config.AppConfig;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.element.MultiGuiElement;
import daksha.tpi.leaping.generator.GuiAutomatorFactory;
import daksha.tpi.leaping.generator.PageFactory;
import daksha.tpi.leaping.generator.selenium.SeleniumBuilder;
import daksha.tpi.leaping.pageobject.App;
import daksha.tpi.leaping.pageobject.Page;
import daksha.tpi.testng.TestNGBaseTest;

public class WebTestWithSeleniumAutomator extends TestNGBaseTest{
	private ThreadLocal<Page> threadWiseHome = new ThreadLocal<Page>();
	
	protected void setCentralOptions() throws Exception {
		Daksha.setOSType(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testContext) throws Exception {
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(Daksha.getTestContext(this.getTestContextName()));
		Page home = new HomePage(builder.build());
		threadWiseHome.set(home);
	}
	
	@Test
	public void test() throws Exception{
		Page page = this.threadWiseHome.get();

		page.goTo(AppConfig.WP_ADMIN_URL);	

		GuiElement userTextBox = page.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(AppConfig.USER_NAME);
		page.element("PASSWORD").enterText(AppConfig.PASSWORD);
		page.element("SUBMIT").click();		
		page.waitForBody();
		
		page = new LeftNavigation(page.getGuiAutomator());
		page.element("POSTS").hover();
		page.element("CATEGORIES").click();	
		page.waitForBody();
		
		page = new Categories(page.getGuiAutomator());
		MultiGuiElement tags = page.elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (GuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}

		page = new LeftNavigation(page.getGuiAutomator());
		page.element("SETTINGS").click();
		
		page = new Settings(page.getGuiAutomator());
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
		this.threadWiseHome.get().close();
	}
}
