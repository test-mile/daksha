package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.element.DefaultGuiMultiElement;
import com.testmile.setu.agent.guiauto.core.element.SeleniumGuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

public class SeleniumElementFinder extends AbstractSeleniumFinder{

	public SeleniumElementFinder(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}
	
	private List<WebElement> findElements(String by, String value) throws Exception{
		By finderType = convertToBy(by, value);
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(finderType));	
	}
	
	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.handler.automator.selenium.ElementFinder#findAll(java.lang.String, java.lang.String)
	 */
	@Override
	public GuiMultiElement findAll(String by, String value) throws Exception {
		return convetToMultiGuiElement(this.findElements(by, value));
	}

	/* (non-Javadoc)
	 * @see com.testmile.setu.agent.guiauto.core.handler.automator.selenium.ElementFinder#find(java.lang.String, java.lang.String)
	 */
	@Override
	public GuiElement find(String by, String value) throws Exception {
		return this.convertToGuiElement(this.findElements(by, value).get(0));
	}
}
