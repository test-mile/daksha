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
package com.testmile.daksha.core.guiauto.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.daksha.core.guiauto.automator.ConcreteGuiAutomator;
import com.testmile.daksha.core.guiauto.element.proxy.GuiElementProxy;
import com.testmile.daksha.tpi.guiauto.gui.Gui;

public class SeleniumGenericElement extends AbstractSeleniumConcreteElement<WebDriver,WebElement>{
	public SeleniumGenericElement(Gui gui, ConcreteGuiAutomator<WebDriver,WebElement> automator, GuiElementProxy eProxy) throws Exception{
		super(gui, automator, eProxy);
	}

	public SeleniumGenericElement(ConcreteGuiAutomator<WebDriver,WebElement> automator, GuiElementProxy eProxy) throws Exception{
		super(automator, eProxy);
	}
	
}
