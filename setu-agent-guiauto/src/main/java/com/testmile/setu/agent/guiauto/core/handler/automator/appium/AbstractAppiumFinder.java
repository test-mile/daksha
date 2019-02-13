package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.element.AppiumGuiElement;
import com.testmile.setu.agent.guiauto.core.element.AppiumGuiMultiElement;
import com.testmile.setu.agent.guiauto.core.element.SeleniumGuiElement;
import com.testmile.setu.agent.guiauto.core.element.SeleniumGuiMultiElement;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.ElementFinder;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.element.GuiMultiElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class AbstractAppiumFinder extends AbstractWDHandler implements ElementFinder {

	protected WebDriverWait wait;
	protected AppiumDriver<MobileElement> appiumDriver;

	public AbstractAppiumFinder(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
		wait = new WebDriverWait(driver, this.getConfig().getMaxWaitTime());
	}
	
	protected GuiElement convertToGuiElement(MobileElement element) throws Exception {
		return new AppiumGuiElement(appiumDriver, element, this.getConfig());
	}

	protected GuiMultiElement convetToMultiGuiElement(List<MobileElement> rawElements) throws Exception {
		List<GuiElement> elements = new ArrayList<GuiElement>();
		for (MobileElement element: rawElements) {
			elements.add(convertToGuiElement(element));
		}
		return new AppiumGuiMultiElement(appiumDriver, elements, rawElements, this.getConfig());
	}
	
	protected AppiumDriver<MobileElement> getAppiumDriver() {
		return this.appiumDriver;
	}

	protected By convertToBy(String by, String value) throws Exception {
		return (new AppiumLocator(by, value)).getByObject();
	}
}