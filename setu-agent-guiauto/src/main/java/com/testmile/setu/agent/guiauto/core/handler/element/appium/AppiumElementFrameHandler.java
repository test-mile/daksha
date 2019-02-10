package com.testmile.setu.agent.guiauto.core.handler.element.appium;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.core.handler.element.selenium.SeleniumElementFrameHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumElementFrameHandler extends SeleniumElementFrameHandler {
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumElementFrameHandler(AppiumDriver<MobileElement> driver, MobileElement element, SetuAgentConfig config) throws Exception {
		super(driver, element, config);
	}

	protected void validateFrameSupport() throws Exception{
		if (HandlerUtils.isWebContextOrWebView(this.getConfig().getAutomationContext(), appiumDriver)){
			throw new Exception("JS execution is supported only for web contexts and views.");
		}
	}

}
