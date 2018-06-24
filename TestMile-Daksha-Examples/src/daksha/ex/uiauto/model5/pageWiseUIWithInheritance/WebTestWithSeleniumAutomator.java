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

package daksha.ex.uiauto.model5.pageWiseUIWithInheritance;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.Daksha;
import daksha.core.cleanup.enums.OSType;
import daksha.ex.config.AppConfig;
import daksha.tpi.cleanup.constructor.UiAutomatorFactory;
import daksha.tpi.cleanup.constructor.selenium.SeleniumBuilder;
import daksha.tpi.cleanup.element.UiElement;
import daksha.tpi.cleanup.element.UiMultiElement;
import daksha.tpi.cleanup.ui.UI;
import daksha.tpi.testng.TestNGBaseTest;

public class WebTestWithSeleniumAutomator extends TestNGBaseTest{
	private ThreadLocal<UI> threadWiseHome = new ThreadLocal<UI>();
	
	protected void setCentralOptions() throws Exception {
		Daksha.setOSType(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testContext) throws Exception {
		SeleniumBuilder builder = UiAutomatorFactory.getSeleniumBuilder(Daksha.getTestContext(this.getTestContextName()));
		UI home = new Home(builder.build());
		threadWiseHome.set(home);
	}
	
	@Test
	public void test() throws Exception{
		UI ui = this.threadWiseHome.get();

		ui.goTo(AppConfig.WP_ADMIN_URL);	

		UiElement userTextBox = ui.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(AppConfig.USER_NAME);
		ui.element("PASSWORD").enterText(AppConfig.PASSWORD);
		ui.element("SUBMIT").click();		
		ui.waitForBody();
		
		ui = new LeftNavigation(ui.getAutomator());
		ui.element("POSTS").hover();
		ui.element("CATEGORIES").click();	
		ui.waitForBody();
		
		ui = new Categories(ui.getAutomator());
		UiMultiElement tags = ui.elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (UiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}

		ui = new LeftNavigation(ui.getAutomator());
		ui.element("SETTINGS").click();
		
		ui = new Settings(ui.getAutomator());
		UiElement blogNameTextBox = ui.element("BLOG_NAME");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		ui.element("MEMBERSHIP").check();

		// Experiments with Select control - Selection using different attributes
		UiElement roleDropDown = ui.element("ROLE").asDropDown();
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");

		ui.goTo(AppConfig.WP_LOGOUT_URL);
	}
	
	@AfterClass
	public void closeAutomator(ITestContext testContext) throws Exception {
		this.threadWiseHome.get().close();
	}
}
