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

package com.testmile.setu.actor.guiauto.core.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.core.handler.element.selenium.SeleniumBasicActionsHandler;
import com.testmile.setu.actor.guiauto.core.handler.element.selenium.SeleniumElementInquirer;
import com.testmile.setu.actor.guiauto.core.handler.element.selenium.SeleniumElementStateHandler;
import com.testmile.setu.actor.guiauto.core.handler.element.selenium.SeleniumNestedElementFinder;

public class SeleniumGuiElement extends AbstractGuiElement{
	private WebDriver driver;
	private WebElement element;
	private SetuActorConfig config;
	
	public SeleniumGuiElement(WebDriver driver, WebElement element, SetuActorConfig config) throws Exception {
		this.driver = driver;
		this.element = element;
		this.config = config;

		setBasicActionsHandler(new SeleniumBasicActionsHandler(driver, element, config));
		setInquirer(new SeleniumElementInquirer(driver, element, config));
		setStateHandler(new SeleniumElementStateHandler(driver, element, config));
		setElementFinder(new SeleniumNestedElementFinder(driver, element, config));
	}

	
	public void switchToFrame() throws Exception{
		this.driver.switchTo().frame(element);
	}
}
