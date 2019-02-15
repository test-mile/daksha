package com.testmile.setu.agent.guiauto.core.handler.automator.appium;
import java.util.Set;

import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.HybridViewHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumViewHandler extends AbstractWDHandler implements HybridViewHandler{
	private AppiumDriver<MobileElement> appiumDriver;

	public AppiumViewHandler(AppiumDriver<MobileElement>  driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
	}

	private AppiumDriver<MobileElement>  getAppiumDriver(){
		return this.appiumDriver;
	}
	
	public void switchToViewContext(String view) throws Exception {
		this.getAppiumDriver().context(view);
	}

	public String getCurrentViewContext() throws Exception {
		return this.getAppiumDriver().getContext();
	}
	
	public Set<String> getAllViewContexts() throws Exception {
		return this.getAppiumDriver().getContextHandles();
	}
	
}
