package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumJSExecutor;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumJSExecutor  extends SeleniumJSExecutor{
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumJSExecutor(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.appiumDriver = driver;
	}

	protected void validateJSSupport() throws Exception{
		if (HandlerUtils.isWebContextOrWebView(this.getConfig().getAutomationContext(), appiumDriver)){
			throw new Exception("JS execution is supported only for web contexts and views.");
		}
	}
}
