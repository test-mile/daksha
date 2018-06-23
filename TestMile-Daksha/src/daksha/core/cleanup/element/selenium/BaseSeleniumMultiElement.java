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
package daksha.core.cleanup.element.selenium;

import org.openqa.selenium.WebElement;

import daksha.core.cleanup.automator.ConcreteGuiAutomator;
import daksha.core.cleanup.element.BaseConcreteMultiGuiElement;
import daksha.core.cleanup.element.proxy.MultiGuiElementProxy;
import daksha.tpi.cleanup.gui.Gui;

public class BaseSeleniumMultiElement<D,E> extends BaseConcreteMultiGuiElement<D,E>{

	public BaseSeleniumMultiElement(Gui gui, ConcreteGuiAutomator<D,E> automator, MultiGuiElementProxy eProxy){
		super(gui, automator, eProxy);
	}
	
	public BaseSeleniumMultiElement(ConcreteGuiAutomator<D,E> automator, MultiGuiElementProxy eProxy){
		this(null, automator, eProxy);
	}
	
	protected boolean doesTextMatch(E element, String text) throws Exception{
		return ((WebElement)element).getText().equals(text);
	}
	protected boolean doesTextContain(E element, String text) throws Exception{
		return ((WebElement)element).getText().contains(text);
	}
	
}
