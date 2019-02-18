package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.element.ElementInquirer;

public class SeleniumElementInquirer extends AbstractWDElementHandler implements ElementInquirer {

	public SeleniumElementInquirer(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}

	@Override
	public String getTextContent() throws Exception {
		return this.getWebElement().getText();
	}

	@Override
	public String getAttribute(String attr) throws Exception {
		return this.getWebElement().getAttribute(attr);
	}
	
	@Override
	public String getTagName() throws Exception {
		return this.getWebElement().getTagName();
	}

}
