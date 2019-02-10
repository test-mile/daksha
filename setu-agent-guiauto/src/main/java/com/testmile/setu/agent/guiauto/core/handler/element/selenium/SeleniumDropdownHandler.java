package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.DropdownHandler;

public class SeleniumDropdownHandler extends AbstractWDElementHandler implements DropdownHandler {
	private Select selectElement;
	
	public SeleniumDropdownHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
		this.selectElement = new Select(element);
	}
	
	private Select getSelectElement() throws Exception {
		return this.selectElement;
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#select(java.lang.String)
	 */
	@Override
	public void select(String text) throws Exception{
		this.getSelectElement().selectByVisibleText(text);	
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#selectText(java.lang.String)
	 */
	@Override
	public void selectText(String text) throws Exception{
		this.getSelectElement().selectByVisibleText(text);	
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#selectValue(java.lang.String)
	 */
	@Override
	public void selectValue(String value) throws Exception{
		this.getSelectElement().selectByValue(value);		
	}
	
	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#selectAtIndex(int)
	 */
	@Override
	public void selectAtIndex(int index) throws Exception{
		this.getSelectElement().selectByIndex(index);	
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#hasSelectedText(java.lang.String)
	 */
	@Override
	public boolean hasSelectedText(String text) throws Exception{
		List<WebElement> selectedOptions = this.getSelectElement().getAllSelectedOptions();
		for (WebElement option: selectedOptions){
			if (option.getText().equals(text)) return true;
		}
		return false;	
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#hasSelectedValue(java.lang.String)
	 */
	@Override
	public boolean hasSelectedValue(String value) throws Exception{
		List<WebElement> selectedOptions = this.getSelectElement().getAllSelectedOptions();
		for (WebElement option: selectedOptions){
			if (option.getAttribute("value").equals(value)) return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#hasSelectedIndex(int)
	 */
	@Override
	public boolean hasSelectedIndex(int index) throws Exception{
		List<WebElement> options = this.getSelectElement().getOptions();
		return options.get(index).isSelected();
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#getAllOptions()
	 */
	@Override
	public List<String> getAllOptions() throws Exception{
		List<String> texts = new ArrayList<String>();
		for (WebElement option: this.getSelectElement().getOptions()){
			texts.add(option.getText());
		}
		return texts;	
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#getAllValues()
	 */
	@Override
	public List<String> getAllValues() throws Exception{
		List<String> values = new ArrayList<String>();
		for (WebElement option: this.getSelectElement().getOptions()){
			values.add(option.getAttribute("value"));
		}
		return values;		
	}

	/* (non-Javadoc)
	 * @see a.com.testmile.setu.agent.guiauto.ca.core.handlers.element.selenium.DropdownHandler#getOptionCount()
	 */
	@Override
	public int getOptionCount() throws Exception{
		return this.getSelectElement().getOptions().size();
	}

}
