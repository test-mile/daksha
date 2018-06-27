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
package daksha.core.uiauto.automator.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.batteries.config.TestContext;
import daksha.core.uiauto.enums.GuiElementLoaderType;
import daksha.core.uiauto.enums.GuiDriverEngine;
import daksha.core.uiauto.identifier.selenium.SeleniumElementIdentifier;
import daksha.tpi.uiauto.enums.GuiAutomationContext;

public class SeleniumWebGuiDriver extends BaseSeleniumWebGuiDriver<WebDriver,WebElement>{
	
	public SeleniumWebGuiDriver(TestContext testContext, GuiElementLoaderType loaderType) throws Exception{
		super(testContext, GuiDriverEngine.WEBDRIVER, GuiAutomationContext.PC_WEB, loaderType);
	}
		
	public SeleniumWebGuiDriver(TestContext testContext) throws Exception{
		this(testContext, GuiElementLoaderType.AUTOMATOR);
	}

	public void load() throws Exception {
		super.load();
		this.setIdentifier(new SeleniumElementIdentifier(this));
	}
	
}