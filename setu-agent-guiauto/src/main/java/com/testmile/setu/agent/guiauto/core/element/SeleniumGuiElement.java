package com.testmile.setu.agent.guiauto.core.element;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumBasicActionsHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumElementFrameHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumElementInquirer;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumElementStateHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumNestedElementFinder;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumRadioButtonHandler;

public class SeleniumGuiElement extends AbstractGuiElement{
	private WebDriver driver;
	private WebElement element;
	private SetuAgentConfig config;
	
	public SeleniumGuiElement(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		this.driver = driver;
		this.element = element;
		this.config = config;
		setBasicActionsHandler(new SeleniumBasicActionsHandler(driver, element, config));
		setFrameHandler(new SeleniumElementFrameHandler(driver, element, config));
		setInquirer(new SeleniumElementInquirer(driver, element, config));
		setStateHandler(new SeleniumElementStateHandler(driver, element, config));
		setRadioHandler(new SeleniumRadioButtonHandler(driver, element, config));
		setElementFinder(new SeleniumNestedElementFinder(driver, element, config));
	}

}
