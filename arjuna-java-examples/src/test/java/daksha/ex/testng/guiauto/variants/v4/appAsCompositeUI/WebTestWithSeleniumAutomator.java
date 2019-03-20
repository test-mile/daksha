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

package daksha.ex.testng.guiauto.variants.v4.appAsCompositeUI;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.testmile.arjuna.lib.config.DakshaTestContext;
import com.testmile.arjuna.lib.enums.OSType;
import com.testmile.arjuna.tpi.guiauto.element.SetuClientGuiElement;
import com.testmile.arjuna.tpi.guiauto.element.SetuClientGuiMultiElement;
import com.testmile.arjuna.tpi.guiauto.gui.GuiFactory;
import com.testmile.arjuna.tpi.guiauto.gui.SetuClientGui;
import com.testmile.arjuna.tpi.testng.TestNGBaseTest;
import com.testmile.setu.actor.guiauto.core.core.builder.SeleniumBuilder;

public class WebTestWithSeleniumAutomator extends TestNGBaseTest{
	private ThreadLocal<SetuClientGui> threadWiseApp = new ThreadLocal<SetuClientGui>();
	
	protected void tweakCentralContext(DakshaTestContext centralContext)  throws Exception {
		centralContext.setTargetPlatform(OSType.MAC);
	}
	
	protected void setUpClass(DakshaTestContext testContext) throws Exception {
		SeleniumBuilder builder = new SeleniumBuilder(testContext);
		SetuClientGui app = GuiFactory.createAppFromDir("WordPress", builder.build(), "appwithpages");
		threadWiseApp.set(app);
	}
	
	@Test
	public void test() throws Exception{
		SetuClientGui app = this.threadWiseApp.get();

		app.goTo(this.getContext().getValue("wp.admin.url").asString());	

		SetuClientGuiElement userTextBox = app.element("LOGIN");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(this.getContext().getValue("wp.username").asString());
		app.element("PASSWORD").enterText(this.getContext().getValue("wp.password").asString());
		app.element("SUBMIT").click();		
		app.waitForBody();
		
		app.gui("LeftNavigation").element("POSTS").hover();
		app.gui("LeftNavigation").element("CATEGORIES").click();	
		app.waitForBody();
		
		SetuClientGuiMultiElement tags = app.gui("Categories").elements("CAT_CHECKBOXES");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
			
		for (SetuClientGuiElement element: tags.getAllInstances()){
			element.check();
			element.uncheck();
		}

		app.gui("LeftNavigation").element("SETTINGS").click();

		SetuClientGuiElement blogNameTextBox = app.gui("Settings").element("BLOG_NAME");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		app.gui("Settings").element("MEMBERSHIP").check();

		// Experiments with Select control - Selection using different attributes
		SetuClientGuiElement roleDropDown = app.gui("Settings").element("ROLE");
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");

		app.goTo(this.getContext().getValue("wp.logout.url").asString());
	}
	
	public void tearDownClass(DakshaTestContext testContext) throws Exception {
		this.threadWiseApp.get().close();
	}
}
