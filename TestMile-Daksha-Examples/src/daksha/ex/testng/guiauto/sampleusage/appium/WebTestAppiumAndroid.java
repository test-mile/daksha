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

package daksha.ex.testng.guiauto.sampleusage.appium;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.core.guiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.guiauto.enums.OSType;
import daksha.ex.config.AppConfig;
import daksha.tpi.CentralTestContext;
import daksha.tpi.TestContext;
import daksha.tpi.guiauto.element.GuiElement;
import daksha.tpi.guiauto.element.GuiMultiElement;
import daksha.tpi.guiauto.enums.GuiAutomationContext;
import daksha.tpi.guiauto.maker.GuiAutomatorFactory;
import daksha.tpi.guiauto.maker.appium.AppiumBuilder;
import daksha.tpi.testng.TestNGBaseTest;

public class WebTestAppiumAndroid extends TestNGBaseTest{
	private ThreadLocal<GuiAutomatorProxy> threadWiseAutomator = new ThreadLocal<GuiAutomatorProxy>();
	
	protected void tweakCentralOptions(CentralTestContext centralContext) throws Exception {
		centralContext.setTargetPlatform(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testNGContext) throws Exception {
		TestContext context = this.getContext();
		context.setAutomationContext(GuiAutomationContext.ANDROID_WEB);
		AppiumBuilder builder = GuiAutomatorFactory.getAppiumBuilder(context);
		this.threadWiseAutomator.set(builder.build());
	}
	
	@Test
	public void test() throws Exception{
		GuiAutomatorProxy automator = this.threadWiseAutomator.get();
		automator.goTo(AppConfig.WP_ADMIN_URL);	
		
		GuiElement userTextBox = automator.elementWithId("user_login");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(AppConfig.USER_NAME);
		automator.elementWithId("user_pass").enterText(AppConfig.PASSWORD);
		automator.elementWithName("wp-submit").click();
		
		automator.elementWithId("wp-admin-bar-menu-toggle").click();
		automator.elementWithXText("Posts").click();
		automator.elementWithLinkText("Categories").click();
		
		GuiMultiElement tags = automator.elementsWithName("delete_tags[]");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
		
		for (GuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}
		
		// Tests for alternate instance methods
		tags.getFirstInstance().check();
		tags.getFirstInstance().uncheck();
		
		tags.getLastInstance().check();
		tags.getLastInstance().uncheck();
		
		
		tags.getRandomInstance().check();
		tags.getRandomInstance().uncheck();
	
		automator.elementWithId("wp-admin-bar-menu-toggle").click();
		automator.elementWithXText("Settings").click();
		automator.elementWithLinkText("General").click();
		
		GuiElement blogNameTextBox = automator.elementWithId("blogname");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		automator.elementWithId("users_can_register").check();

		GuiElement roleDropDown = automator.elementWithId("default_role").asDropDown();
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");
		
		automator.goTo(AppConfig.WP_LOGOUT_URL);
	}
	
	@AfterClass
	public void closeAutomator(ITestContext testContext) throws Exception {
		this.threadWiseAutomator.get().close();
	}
}
