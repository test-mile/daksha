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
package daksha.core.leaping.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.pageobject.Page;

public class SeleniumDropdownElement extends BaseSeleniumDropdownElement<WebDriver,WebElement>{
	
	public SeleniumDropdownElement(Page page, GuiAutomator<WebDriver,WebElement> automator, GuiElementProxy eProxy) throws Exception{
		super(page,automator, eProxy);
	}
	
	public SeleniumDropdownElement(GuiAutomator<WebDriver,WebElement> automator, GuiElementProxy eProxy) throws Exception{
		this(null, automator, eProxy);
	}
}