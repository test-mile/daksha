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

import com.testmile.setu.actor.guiauto.core.tpi.automator.GuiAutomator;
import com.testmile.setu.actor.guiauto.core.tpi.builder.GuiAutomatorFactory;
import com.testmile.setu.actor.guiauto.core.tpi.element.GuiElement;
import com.testmile.setu.actor.guiauto.core.tpi.element.GuiMultiElement;
import com.testmile.setu.actor.guiauto.core.tpi.handler.element.DropdownHandler;

import arjuna.lib.setu.actor.JsonUtils;


/*
 * Re-usable values are condigured in Setu Client and Setu.
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
		
		automator.getElementFinder().find("link_text", "Posts").getBasicActionsHandler().click();
		automator.getElementFinder().find("link_text", "Categories").getBasicActionsHandler().click();
		
		GuiMultiElement checkboxes = automator.getElementFinder().findAll("name", "delete_tags[]");
		checkboxes.IndexedElement(0).getCheckBoxHandler().check();
		checkboxes.IndexedElement(0).getCheckBoxHandler().uncheck();

		automator.getElementFinder().find("link_text", "Settings").getBasicActionsHandler().click();
		GuiElement blogName = automator.getElementFinder().find("id", "blogname");
		blogName.getBasicActionsHandler().sendText("Hello");
		blogName.getBasicActionsHandler().sendText("Hello");
		blogName.getBasicActionsHandler().setText("Hello");
		
		automator.getElementFinder().find("id", "users_can_register").getCheckBoxHandler().check();
		
		DropdownHandler roleDropDown = automator.getElementFinder().find("id", "default_role").asDropDown();

		roleDropDown.selectText("Author");
		assertTrue(roleDropDown.hasSelectedText("Author"), "Check Author Role Selected");
		roleDropDown.selectAtIndex(0);
		assertTrue(roleDropDown.hasSelectedIndex(0), "Check Author Role Selected");
		roleDropDown.selectValue("author");
		assertTrue(roleDropDown.hasSelectedValue("author"), "Check Author Role Selected");
		
		automator.getBrowserHandler().goTo("http://192.168.56.103/wp-login.php?action=logout");
		
		automator.quit();
	}
}
