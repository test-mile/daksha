package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementFrameHandler;

public class SeleniumElementFrameHandler extends AbstractWDElementHandler implements ElementFrameHandler {

	public SeleniumElementFrameHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}
	
	protected void validateFrameSupport() throws Exception{
		return;
	}

	@Override
	public void switchToFrame() throws Exception {
		validateFrameSupport();
		getWebDriver().switchTo().frame(this.getWebElement());
	}

}
