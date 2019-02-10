package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumAlertHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumAlertHandler extends SeleniumAlertHandler{
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumAlertHandler(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.appiumDriver = driver;
	}

	protected void validateAlertSupport() throws Exception{
		if (HandlerUtils.isWebContextOrWebView(this.getConfig().getAutomationContext(), appiumDriver)){
			throw new Exception("Alert handling is supported only for web contexts and views.");
		}
	}
}
