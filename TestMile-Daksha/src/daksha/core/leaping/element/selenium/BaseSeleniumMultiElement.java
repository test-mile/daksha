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

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import daksha.core.leaping.element.BaseConcreteMultiGuiElement;
import daksha.core.leaping.element.BaseConcreteSingleGuiElement;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.proxy.MultiGuiElementProxy;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.leaping.pageobject.Page;

public class BaseSeleniumMultiElement<D,E> extends BaseConcreteMultiGuiElement<D,E>{

	public BaseSeleniumMultiElement(Page page, GuiAutomator<D,E> automator, MultiGuiElementProxy eProxy){
		super(page, automator, eProxy);
	}
	
	public BaseSeleniumMultiElement(GuiAutomator<D,E> automator, MultiGuiElementProxy eProxy){
		this(null, automator, eProxy);
	}
	
	protected boolean doesTextMatch(E element, String text) throws Exception{
		return ((WebElement)element).getText().equals(text);
	}
	protected boolean doesTextContain(E element, String text) throws Exception{
		return ((WebElement)element).getText().contains(text);
	}
	
}
