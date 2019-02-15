package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.BasicActionsHandler;

public class SeleniumBasicActionsHandler extends AbstractWDElementHandler implements BasicActionsHandler {

	public SeleniumBasicActionsHandler(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}

	@Override
	public void sendText(String text) throws Exception {
		this.getWebElement().sendKeys(text);
	}

	@Override
	public void clearText() throws Exception {
		this.getWebElement().clear();
	}

	@Override
	public void submit() throws Exception {
		this.getWebElement().submit();
	}

	@Override
	public void click() throws Exception {
		this.getWebElement().click();
	}

}
