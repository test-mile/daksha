package com.testmile.setu.agent.guiauto.core.element;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.appium.AppiumElementFrameHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.appium.AppiumMultiElementInquirer;
import com.testmile.setu.agent.guiauto.core.handler.element.appium.AppiumMultiElementStateHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.appium.AppiumNestedElementFinder;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumBasicActionsHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumDropdownHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumElementInquirer;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumElementStateHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumMultiElementInquirer;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumMultiElementStateHandler;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumRadioButtonHandler;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;
import com.testmile.setu.agent.guiauto.tpi.handler.element.DropdownHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumGuiMultiElement extends AbstractGuiMultiElement{
	private AppiumDriver<MobileElement> driver;
	private MobileElement element;
	private SetuAgentConfig config;
	
	public AppiumGuiMultiElement(AppiumDriver<MobileElement> driver, List<GuiElement> elements, List<MobileElement> rawElements, SetuAgentConfig config) throws Exception {
			super(elements);
			setInquirer(new AppiumMultiElementInquirer(driver, rawElements, config));
			setStateHandler(new AppiumMultiElementStateHandler(driver, rawElements, config));
	}

}
