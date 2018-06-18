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
package daksha.core.leaping.automator.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.batteries.config.TestContext;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.UiDriverEngine;
import daksha.core.leaping.identifier.selenium.SeleniumGuiElementIdentifier;
import daksha.tpi.leaping.enums.GuiAutomationContext;

public class SeleniumWebUiDriver extends BaseSeleniumWebUiDriver<WebDriver,WebElement>{
	
	public SeleniumWebUiDriver(TestContext testContext, ElementLoaderType loaderType) throws Exception{
		super(testContext, UiDriverEngine.WEBDRIVER, GuiAutomationContext.PC_WEB, loaderType);
		this.setIdentifier(new SeleniumGuiElementIdentifier(this));
	}
		
	public SeleniumWebUiDriver(TestContext testContext) throws Exception{
		this(testContext, ElementLoaderType.AUTOMATOR);
	}
	
}