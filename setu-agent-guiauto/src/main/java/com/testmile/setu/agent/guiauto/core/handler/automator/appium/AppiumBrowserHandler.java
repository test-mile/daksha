package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumBrowserHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumBrowserHandler extends SeleniumBrowserHandler {
	AppiumDriver<MobileElement> appiumDriver;

	public AppiumBrowserHandler(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
	}
	
	protected void validateBrowserSupport() throws Exception{
		if (HandlerUtils.isWebContextOrWebView(this.getConfig().getAutomationContext(), appiumDriver)){
			throw new Exception("Browser actions are supported only for web contexts and views.");
		}
	}

}
