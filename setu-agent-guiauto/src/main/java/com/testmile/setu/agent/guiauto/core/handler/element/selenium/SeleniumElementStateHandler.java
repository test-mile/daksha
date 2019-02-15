package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementStateHandler;

public class SeleniumElementStateHandler extends AbstractWDElementHandler implements ElementStateHandler {

	public SeleniumElementStateHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}
	
	public boolean isVisible() {
		return this.getWebElement().isDisplayed();
	}
	
	public boolean isClickable() {
		return this.getWebElement().isEnabled();		
	}
	
	public boolean isSelected() throws Exception{
		return this.getWebElement().isSelected();		
	}
}
