package com.testmile.setu.agent.guiauto.core.handler.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;

public abstract class AbstractWDElementHandler extends AbstractWDHandler {
	private WebElement webElement;

	public AbstractWDElementHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.webElement = element;
	}

	protected WebElement getWebElement() {
		return webElement;
	}
	
}
