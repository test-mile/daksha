package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.element.SeleniumGuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;

public class SeleniumElementFinder extends AbstractSeleniumFinder<WebElement>{

	public SeleniumElementFinder(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}
	
	protected GuiElement convertToGuiElement(WebElement element) throws Exception {
		return new SeleniumGuiElement(this.getWebDriver(), element, this.getConfig());
	}
	
	protected List<WebElement> findAllInContainer(By by) throws Exception{
		return this.getWebDriver().findElements(by);
	}
	
	protected By getLocator(String by, String value) throws Exception{
		return (new SeleniumLocator(by, value)).getByObject();
	}
}
