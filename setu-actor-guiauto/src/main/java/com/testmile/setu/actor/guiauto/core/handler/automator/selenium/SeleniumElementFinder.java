/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
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

package com.testmile.setu.actor.guiauto.core.handler.automator.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.actor.SetuAgentConfig;
import com.testmile.setu.actor.guiauto.core.element.SeleniumGuiElement;
import com.testmile.setu.actor.guiauto.tpi.element.GuiElement;

public class SeleniumElementFinder extends AbstractSeleniumFinder<WebElement>{

	public SeleniumElementFinder(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}
	
	protected GuiElement convertToGuiElement(WebElement element) throws Exception {
		return new SeleniumGuiElement(this.getWebDriver(), element, this.getConfig());
	}
	
	protected List<WebElement> findAllInContainer(By by) throws Exception{
		return this.getWebDriver().findElements(by);
	}
	
	protected By getLocator(String by, String value) throws Exception{
		return (new SeleniumLocator(by, value)).getByObject();
	}
}
