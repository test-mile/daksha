package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.HandlerUtils;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumScroller;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.Scroller;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumScroller extends SeleniumScroller implements Scroller{
	AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumScroller(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
	}
	
	private AppiumDriver<MobileElement> getAppiumDriver(){
		return appiumDriver;
	}

	protected void validateScrollSupport() throws Exception {
		if ((GuiAutomationContext.isMobileNativeContext(this.getConfig().getAutomationContext())) || 
				HandlerUtils.isNativeView(this.getAppiumDriver())){
			throw new Exception("Scroll actions are not supported for Native View.");
		}
	}

}
