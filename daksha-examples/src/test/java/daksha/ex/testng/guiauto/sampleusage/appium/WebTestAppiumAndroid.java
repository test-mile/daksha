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

import org.testng.annotations.Test;

import com.testmile.daksha.core.config.DefaultTestContext;
import com.testmile.daksha.core.guiauto.maker.appium.AppiumBuilder;
import com.testmile.daksha.tpi.guiauto.automator.SetuClientGuiAutomator;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiElement;
import com.testmile.daksha.tpi.guiauto.element.SetuClientGuiMultiElement;
import com.testmile.daksha.tpi.testng.TestNGBaseTest;
import com.testmile.trishanku.tpi.enums.GuiAutomationContext;

public class WebTestAppiumAndroid extends TestNGBaseTest{
	private ThreadLocal<SetuClientGuiAutomator> threadWiseAutomator = new ThreadLocal<SetuClientGuiAutomator>();
	
	protected void tweakTestContext(DefaultTestContext testContext)  throws Exception {
		testContext.setAutomationContext(GuiAutomationContext.ANDROID_WEB);
	}
	
	protected void setUpClass(DefaultTestContext testContext) throws Exception {
		AppiumBuilder builder = new AppiumBuilder(testContext);
		this.threadWiseAutomator.set(builder.build());
	}
	
	@Test
	public void test() throws Exception{
		SetuClientGuiAutomator automator = this.threadWiseAutomator.get();
		automator.goTo(this.getContext().getValue("wp.admin.url").asString());	
		
		SetuClientGuiElement userTextBox = automator.elementWithId("user_login");
		userTextBox.waitUntilPresent();
		userTextBox.enterText(this.getContext().getValue("wp.username").asString());
		automator.elementWithId("user_pass").enterText(this.getContext().getValue("wp.password").asString());
		automator.elementWithName("wp-submit").click();
		
		automator.elementWithId("wp-admin-bar-menu-toggle").click();
		automator.elementWithXText("Posts").click();
		automator.elementWithLinkText("Categories").click();
		
		SetuClientGuiMultiElement tags = automator.elementsWithName("delete_tags[]");
		tags.getInstanceAtOrdinal(2).check();
		tags.getInstanceAtIndex(1).uncheck();
		
		for (SetuClientGuiElement element: tags.getAllInstances()){
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
		
		SetuClientGuiElement blogNameTextBox = automator.elementWithId("blogname");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.enterText("Hello");
		blogNameTextBox.setText("Hello");
		
		automator.elementWithId("users_can_register").check();

		SetuClientGuiElement roleDropDown = automator.elementWithId("default_role");
		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");
		
		automator.goTo(this.getContext().getValue("wp.logout.url").asString());
	}
	
	public void tearDownClass(DefaultTestContext testContext) throws Exception {
		this.threadWiseAutomator.get().close();
	}
}
