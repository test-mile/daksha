package com.testmile.setu.agent.guiauto.core.element;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.appium.AppiumElementFrameHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.appium.AppiumNestedElementFinder;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumBasicActionsHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumElementInquirer;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumElementStateHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumRadioButtonHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumGuiElement extends AbstractGuiElement{
	private AppiumDriver<MobileElement> driver;
	private MobileElement element;
	private SetuAgentConfig config;
	
	public AppiumGuiElement(AppiumDriver<MobileElement> driver, MobileElement element, SetuAgentConfig config) throws Exception {
		this.driver = driver;
		this.element = element;
		this.config = config;
		setBasicActionsHandler(new SeleniumBasicActionsHandler(driver, element, config));
		setFrameHandler(new AppiumElementFrameHandler(driver, element, config));
		setInquirer(new SeleniumElementInquirer(driver, element, config));
		setStateHandler(new SeleniumElementStateHandler(driver, element, config));
		setRadioHandler(new SeleniumRadioButtonHandler(driver, element, config));
		setElementFinder(new AppiumNestedElementFinder(driver, element, config));
	}

}
