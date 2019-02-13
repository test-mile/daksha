package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.MultiElementStateHandler;

public class SeleniumMultiElementStateHandler extends AbstractWDHandler implements MultiElementStateHandler {
	private WebDriverWait waiter;
	private List<WebElement> elements;

	public SeleniumMultiElementStateHandler(WebDriver driver, List<WebElement> elements, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.elements = elements;
		this.waiter = new WebDriverWait(driver, this.getConfig().getMaxWaitTime());
	}
	
	public void waitUntilVisible() throws Exception {
		waiter.until(ExpectedConditions.visibilityOfAllElements(this.elements));
	}
	
	public void waitUntilInvisible() throws Exception {
		waiter.until(ExpectedConditions.invisibilityOfAllElements(elements));
	}
	
	public void waitUntilClickable() throws Exception {
		for (WebElement element: elements) {
			waiter.until(ExpectedConditions.elementToBeClickable(element));	
		}
	}
}
