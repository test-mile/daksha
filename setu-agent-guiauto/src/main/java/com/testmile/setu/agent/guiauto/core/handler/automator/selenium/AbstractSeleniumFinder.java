package com.testmile.setu.agent.guiauto.core.handler.automator.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.ElementFinder;

public abstract class AbstractSeleniumFinder extends AbstractFinder<WebElement> implements ElementFinder{
	public AbstractSeleniumFinder(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		wait = new WebDriverWait(driver, this.getConfig().getMaxWaitTime());
	}

	protected By convertToBy(String by, String value) throws Exception {
		return (new SeleniumLocator(by, value)).getByObject();
	}

}