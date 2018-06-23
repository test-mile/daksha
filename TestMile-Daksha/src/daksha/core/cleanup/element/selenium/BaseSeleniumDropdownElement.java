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

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import daksha.core.cleanup.automator.ConcreteGuiAutomator;
import daksha.core.cleanup.element.proxy.GuiElementProxy;
import daksha.tpi.cleanup.gui.Gui;

public class BaseSeleniumDropdownElement<D,E> extends AbstractSeleniumConcreteElement<D,E>{
	
	public BaseSeleniumDropdownElement(Gui page, ConcreteGuiAutomator<D,E> automator, GuiElementProxy eProxy) throws Exception{
		super(page,automator, eProxy);
	}
	
	public BaseSeleniumDropdownElement(ConcreteGuiAutomator<D,E> automator, GuiElementProxy eProxy) throws Exception{
		this(null, automator, eProxy);
	}

	private Select asSelectElement() throws Exception {
		return new Select((WebElement) this.getToolElementWithRetry());
	}
	
	@Override
	public void select(String text) throws Exception{
		this.asSelectElement().selectByVisibleText(text);	
	}
	
	@Override
	public void selectText(String text) throws Exception{
		this.asSelectElement().selectByVisibleText(text);	
	}

	@Override
	public void selectValue(String value) throws Exception{
		this.asSelectElement().selectByValue(value);		
	}
	
	@Override
	public void selectAtIndex(int index) throws Exception{
		this.asSelectElement().selectByIndex(index);	
	}

	@Override
	public boolean hasSelectedText(String text) throws Exception{
		List<WebElement> selectedOptions = this.asSelectElement().getAllSelectedOptions();
		for (WebElement option: selectedOptions){
			if (option.getText().equals(text)) return true;
		}
		return false;	
	}


	@Override
	public boolean hasSelectedValue(String value) throws Exception{
		List<WebElement> selectedOptions = this.asSelectElement().getAllSelectedOptions();
		for (WebElement option: selectedOptions){
			if (option.getAttribute("value").equals(value)) return true;
		}
		return false;
	}


	@Override
	public boolean hasSelectedIndex(int index) throws Exception{
		List<WebElement> options = this.asSelectElement().getOptions();
		return options.get(index).isSelected();
	}

	@Override
	public List<String> getAllOptions() throws Exception{
		List<String> texts = new ArrayList<String>();
		for (WebElement option: this.asSelectElement().getOptions()){
			texts.add(option.getText());
		}
		return texts;	
	}

	@Override
	public List<String> getAllValues() throws Exception{
		List<String> values = new ArrayList<String>();
		for (WebElement option: this.asSelectElement().getOptions()){
			values.add(option.getAttribute("value"));
		}
		return values;		
	}

	@Override
	public int getOptionCount() throws Exception{
		return this.asSelectElement().getOptions().size();
	}

}
