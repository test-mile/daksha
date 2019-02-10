package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractAutomatorHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.functions.ExpectedCondition;

public class AppiumWaiter extends AbstractAutomatorHandler {
	private AppiumDriver<MobileElement> appiumDriver;
	private WebDriverWait wait;

	public AppiumWaiter(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(config);
		wait = new WebDriverWait(appiumDriver, this.getConfig().getMaxWaitTime());
	}
	
	private AppiumDriver<MobileElement> getAppiumDriver(){
		return this.appiumDriver;
	}
	
	public <T> T await(ExpectedCondition<T> condition) {
		return wait.until(condition);
	}

}
