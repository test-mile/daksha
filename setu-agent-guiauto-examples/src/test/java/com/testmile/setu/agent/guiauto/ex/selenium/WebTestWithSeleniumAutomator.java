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

package com.testmile.setu.agent.guiauto.ex.selenium;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.testmile.daksha.tpi.TestContext;
import com.testmile.setu.agent.guiauto.tpi.automator.GuiAutomator;
import com.testmile.setu.agent.guiauto.tpi.builder.GuiAutomatorFactory;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;
import com.testmile.trishanku.tpi.webserver.JsonUtils;


/*
 * Res-usable values are condigured in Setu Client and Setu.
 * Agent gets some of them in the SetuAgentConfig.
 * Rest are passed as direct values. They appear hard-coded here, that's because agent does not deal with this complexity.
 */

public class WebTestWithSeleniumAutomator{
	
	public static void main (String args[]) throws Exception {
		GuiAutomator automator = GuiAutomatorFactory.createAutomator(JsonUtils.readMavenResource("basicSetu.json"));

		automator.getBrowserHandler().goTo("http://192.168.56.103/wp-admin");
		System.out.println(automator.getWindowHandler().getTitle());
		GuiElement userTextBox = automator.getElementFinder().find("id", "user_login"); 
		userTextBox.getBasicActionsHandler().setText("user");
		
		GuiElement passwordBox = automator.getElementFinder().find("id", "user_pass"); 
		passwordBox.getBasicActionsHandler().setText("bitnami");
		
		GuiElement submitButton = automator.getElementFinder().find("id", "wp-submit");
		submitButton.getBasicActionsHandler().click();
		
		// Wait
		automator.getElementFinder().find("class_name", "welcome-view-site").getStateHandler().waitUntilClickable();
		automator.quit();
	}

//	public void test() throws Exception{
//		SetuClientGuiAutomator automator = this.threadWiseAutomator.get();
//		automator.goTo(this.getContext().getValue().asString());	
//		
//		SetuClientGuiElement userTextBox = automator.elementWithId("user_login");
//		userTextBox.waitUntilPresent();
//		userTextBox.enterText(this.getContext().getValue("wp.username").asString());
//		automator.elementWithId("user_pass").enterText(this.getContext().getValue("wp.password").asString());
//		automator.elementWithId("wp-submit").click();
//		
//		automator.elementWithCss(".dashicons-admin-post").hover();
//		automator.elementWithLinkText("Categories").click();
//		
//		SetuClientGuiMultiElement tags = automator.elementsWithName("delete_tags[]");
//		tags.getInstanceAtOrdinal(2).check();
//		tags.getInstanceAtIndex(1).uncheck();
//		
//		for (SetuClientGuiElement element: tags.getAllInstances()){
//			element.check();
//			element.uncheck();
//		}
//		
//		// Tests for alternate instance methods
//		tags.getFirstInstance().check();
//		tags.getFirstInstance().uncheck();
//		
//		tags.getLastInstance().check();
//		tags.getLastInstance().uncheck();
//		
//		
//		tags.getRandomInstance().check();
//		tags.getRandomInstance().uncheck();
//	
//		automator.elementWithCss(".dashicons-admin-settings").hover();
//		automator.elementWithLinkText("General").click();
//		
//		SetuClientGuiElement blogNameTextBox = automator.elementWithId("blogname");
//		blogNameTextBox.enterText("Hello");
//		blogNameTextBox.enterText("Hello");
//		blogNameTextBox.setText("Hello");
//		
//		automator.elementWithId("users_can_register").check();
//
//		SetuClientGuiElement roleDropDown = automator.elementWithId("default_role");
//		roleDropDown.selectText("Author");
//		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
//		roleDropDown.selectAtIndex(0);
//		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
//		roleDropDown.selectValue("author");
//		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");
//		
//		automator.goTo(this.getContext().getValue("wp.logout.url").asString());
//	}
}
