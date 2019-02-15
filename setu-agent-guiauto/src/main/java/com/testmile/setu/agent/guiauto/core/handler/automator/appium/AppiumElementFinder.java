package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import java.util.List;

import org.openqa.selenium.By;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.element.AppiumGuiElement;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.AbstractSeleniumFinder;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumElementFinder extends AbstractSeleniumFinder<MobileElement> {
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumElementFinder(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.appiumDriver = driver;
	}

	protected GuiElement convertToGuiElement(MobileElement element) throws Exception {
		return new AppiumGuiElement(appiumDriver, element, this.getConfig());
	}
	
	protected List<MobileElement> findAllInContainer(By by) throws Exception{
		return appiumDriver.findElements(by);
	}
	
	protected By getLocator(String by, String value) throws Exception{
		return (new AppiumLocator(by, value)).getByObject();
	}
	
}
