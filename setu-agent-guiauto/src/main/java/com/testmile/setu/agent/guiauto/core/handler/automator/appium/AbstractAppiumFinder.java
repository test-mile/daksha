package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.AbstractFinder;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class AbstractAppiumFinder extends AbstractFinder<MobileElement> implements ElementFinder {

	protected WebDriverWait wait;
	protected AppiumDriver<MobileElement> appiumDriver;

	public AbstractAppiumFinder(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
		wait = new WebDriverWait(driver, this.getConfig().getMaxWaitTime());
	}
	
	protected AppiumDriver<MobileElement> getAppiumDriver() {
		return this.appiumDriver;
	}

	protected By convertToBy(String by, String value) throws Exception {
		return (new AppiumLocator(by, value)).getByObject();
	}
}