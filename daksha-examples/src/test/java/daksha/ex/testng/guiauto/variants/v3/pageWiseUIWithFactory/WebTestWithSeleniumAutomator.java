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

package daksha.ex.testng.guiauto.variants.v3.pageWiseUIWithFactory;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.testmile.daksha.core.guiauto.maker.selenium.SeleniumBuilder;
import com.testmile.daksha.tpi.TestContext;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiElement;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiMultiElement;
import com.testmile.daksha.tpi.guiauto.gui.GuiFactory;
import com.testmile.daksha.tpi.guiauto.gui.SetuClientGui;
import com.testmile.daksha.tpi.testng.TestNGBaseTest;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

public class WebTestWithSeleniumAutomator extends TestNGBaseTest{
	private ThreadLocal<SetuClientGui> threadWiseHomePage = new ThreadLocal<SetuClientGui>();
	
	protected void tweakCentralContext(TestContext centralContext)  throws Exception {
		centralContext.setTargetPlatform(OSType.MAC);
	}
	
	protected void setUpClass(TestContext testContext) throws Exception {
		SeleniumBuilder builder = new SeleniumBuilder(testContext);
		SetuClientGui home = GuiFactory.createGui(builder.build(), "wordpress/Home.gns");
		threadWiseHomePage.set(home);
	}
	
	@Test
	public void test() throws Exception{
		SetuClientGui gui =  this.threadWiseHomePage.get();

		gui.goTo(this.getContext().getValue("wp.admin.url").asString());	

		SetuClientGuiElement userTextBox = gui.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(this.getContext().getValue("wp.username").asString());
		gui.element("PASSWORD").enterText(this.getContext().getValue("wp.password").asString());
		gui.element("SUBMIT").click();		
		gui.waitForBody();
		
		gui = GuiFactory.createGui(gui.getAutomator(), "wordpress/LeftNavigation.gns");
		gui.element("POSTS").hover();
		gui.element("CATEGORIES").click();	
		gui.waitForBody();
		
		gui = GuiFactory.createGui(gui.getAutomator(), "wordpress/Categories.gns");
		SetuClientGuiMultiElement tags = gui.elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (SetuClientGuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}

		gui = GuiFactory.createGui(gui.getAutomator(), "wordpress/LeftNavigation.gns");
		gui.element("SETTINGS").click();
		
		gui = GuiFactory.createGui(gui.getAutomator(), "wordpress/Settings.gns");
		SetuClientGuiElement blogNameTextBox = gui.element("BLOG_NAME");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		gui.element("MEMBERSHIP").check();

		// Experiments with Select control - Selection using different attributes
		SetuClientGuiElement roleDropDown = gui.element("ROLE");
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");

		gui.goTo(this.getContext().getValue("wp.logout.url").asString());
	}
	
	public void tearDownClass(TestContext testContext) throws Exception {
		this.threadWiseHomePage.get().close();
	}
}
