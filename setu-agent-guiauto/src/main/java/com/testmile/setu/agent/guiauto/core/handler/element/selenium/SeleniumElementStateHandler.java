package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementStateHandler;

public class SeleniumElementStateHandler extends AbstractWDElementHandler implements ElementStateHandler {
	private WebDriverWait waiter;

	public SeleniumElementStateHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
		this.waiter = new WebDriverWait(driver, this.getConfig().getMaxWaitTime());
	}
	
	public void waitUntilVisible() throws Exception {
		waiter.until(ExpectedConditions.visibilityOf(this.getWebElement()));
	}
	
	public void waitUntilInvisible() throws Exception {
		waiter.until(ExpectedConditions.invisibilityOf(this.getWebElement()));
	}
	
	public void waitUntilClickable() throws Exception {
		waiter.until(ExpectedConditions.elementToBeClickable(this.getWebElement()));
	}
	
	public boolean isVisible() {
		return this.getWebElement().isDisplayed();
	}
	
	public boolean isInvisible() throws Exception {
		return !isVisible();
	}
	
	public boolean isClickable() {
		return this.getWebElement().isEnabled();		
	}
	
	public boolean isSelected() throws Exception{
		return this.getWebElement().isSelected();		
	}
}
