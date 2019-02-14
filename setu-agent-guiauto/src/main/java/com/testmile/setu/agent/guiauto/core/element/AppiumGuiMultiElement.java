package com.testmile.setu.agent.guiauto.core.element;

import java.util.List;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.element.appium.AppiumMultiElementInquirer;
import com.testmile.setu.agent.guiauto.core.handler.element.appium.AppiumMultiElementStateHandler;
import com.testmile.setu.agent.guiauto.tpi.element.GuiElement;

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
