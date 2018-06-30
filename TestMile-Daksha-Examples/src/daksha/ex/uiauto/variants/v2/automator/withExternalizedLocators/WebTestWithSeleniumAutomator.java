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
 * Unless required by automatorlicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package daksha.ex.uiauto.variants.v2.automator.withExternalizedLocators;

import static org.testng.Assert.assertTrue;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import daksha.Daksha;
import daksha.core.batteries.config.TestContext;
import daksha.core.uiauto.automator.proxy.GuiAutomatorProxy;
import daksha.core.uiauto.enums.OSType;
import daksha.core.uiauto.identifier.GuiElementMetaData;
import daksha.core.uiauto.namestore.GuiNamespace;
import daksha.ex.config.AppConfig;
import daksha.tpi.testng.TestNGBaseTest;
import daksha.tpi.uiauto.element.GuiElement;
import daksha.tpi.uiauto.element.GuiMultiElement;
import daksha.tpi.uiauto.maker.GuiAutomatorFactory;
import daksha.tpi.uiauto.maker.GuiNamespaceLoaderFactory;
import daksha.tpi.uiauto.maker.namestore.GuiNamespaceLoader;
import daksha.tpi.uiauto.maker.selenium.SeleniumBuilder;

public class WebTestWithSeleniumAutomator extends TestNGBaseTest{
	private ThreadLocal<GuiAutomatorProxy> threadWiseAutomator = new ThreadLocal<GuiAutomatorProxy>();
	private ThreadLocal<GuiNamespace> threadWiseNamespace = new ThreadLocal<GuiNamespace>();
	
	protected void setCentralOptions() throws Exception {
		Daksha.setOSType(OSType.MAC);
	}
	
	@BeforeClass
	public void createAutomator(ITestContext testContext) throws Exception {
		TestContext context = Daksha.getTestContext(this.getTestContextName());
		SeleniumBuilder builder = GuiAutomatorFactory.getSeleniumBuilder(context);
		this.threadWiseAutomator.set(builder.build());
		GuiNamespaceLoader loader = GuiNamespaceLoaderFactory.createNamespaceLoader(context, "simpleapp/wordpress.gns");
		loader.load();
		this.threadWiseNamespace.set(loader.getNamespace());
	}
	
	private GuiElementMetaData getEData(String name) throws Exception {
		return this.threadWiseNamespace.get().getMetaData(name, this.threadWiseAutomator.get().getAutomatorContext());
	}
	
	@Test
	public void test() throws Exception{
		GuiAutomatorProxy automator = this.threadWiseAutomator.get();
		
		automator.goTo(AppConfig.WP_ADMIN_URL);	

		GuiElement userTextBox = automator.element(getEData("LOGIN"));
		userTextBox.waitUntilPresent();
		userTextBox.enterText(AppConfig.USER_NAME);
		automator.element(getEData("PASSWORD")).enterText(AppConfig.PASSWORD);
		automator.element(getEData("SUBMIT")).click();		
		automator.waitForBody();
		
		automator.element(getEData("POSTS")).hover();
		automator.element(getEData("CATEGORIES")).click();	

		automator.waitForBody();
		GuiMultiElement tags = automator.elements(getEData("CAT_CHECKBOXES"));
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (GuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}

		automator.element(getEData("SETTINGS")).click();
			
		GuiElement blogNameTextBox = automator.element(getEData("BLOG_NAME"));
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		automator.element(getEData("MEMBERSHIP")).check();

		// Experiments with Select control - Selection using different attributes
		GuiElement roleDropDown = automator.element(getEData("ROLE")).asDropDown();
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
