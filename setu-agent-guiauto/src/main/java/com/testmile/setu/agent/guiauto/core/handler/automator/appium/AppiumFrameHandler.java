package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumFrameHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.FrameHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumFrameHandler extends SeleniumFrameHandler implements FrameHandler{
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumFrameHandler(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
	}

	protected void validateFrameSupport() throws Exception{
		if (HandlerUtils.isWebContextOrWebView(this.getConfig().getAutomationContext(), appiumDriver)){
			throw new Exception("JS execution is supported only for web contexts and views.");
		}
	}

}
