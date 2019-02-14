package com.testmile.setu.agent.guiauto.core.handler.element.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.AbstractSeleniumFinder;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public class SeleniumNestedElementFinder extends AbstractSeleniumFinder{
	private WebElement webElement;

	public SeleniumNestedElementFinder(WebDriver driver, WebElement element, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.webElement = element;
	}
	
	private List<WebElement> findElements(String by, String value) throws Exception{
		By finderType = convertToBy(by, value);
		wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(webElement, finderType));
		return webElement.findElements(finderType);		
	}

	public GuiMultiElement findAll(String by, String value) throws Exception {
		return this.convetToMultiGuiElement(findElements(by, value));
	}

	public GuiElement find(String by, String value) throws Exception {
		return this.convertToGuiElement(this.findElements(by, value).get(0));
	}
}
