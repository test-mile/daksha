package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumElementFinder;

public class SeleniumNestedElementFinder extends SeleniumElementFinder{
	private WebElement webElement;

	public SeleniumNestedElementFinder(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.webElement = element;
	}
	
	protected List<WebElement> findAllInContainer(By by) throws Exception{
		return this.webElement.findElements(by);
	}
}
