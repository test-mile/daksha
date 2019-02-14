package com.testmile.setu.agent.guiauto.core.handler.automator.appium;

import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumScreenshoter;
import com.testmile.setu.agent.guiauto.tpi.handler.automator.HybridViewHandler;
import com.testmile.trishanku.tpi.guiauto.enums.MobileView;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumScreenshoter extends SeleniumScreenshoter {
	private AppiumDriver<MobileElement> appiumDriver;
	private HybridViewHandler viewHandler;
	
	public AppiumScreenshoter(AppiumDriver<MobileElement> driver, SetuAgentConfig config, HybridViewHandler viewHandler) throws Exception {
		super(driver, config);
		appiumDriver = driver;
		this.viewHandler = viewHandler;
	}
	
	public String takeScreenshot() throws Exception {
		// Mobile Web
		if (GuiAutomationContext.isMobileWebContext(this.getConfig().getAutomationContext())) {
			return super.takeScreenshot();
		}
		
		// Mobile native. Could be in web view context.
		if (this.getConfig().getOSType().equals(OSType.ANDROID)) {
			String context = this.appiumDriver.getContext();
			if (context.contains(MobileView.NATIVE_APP.toString())) {
				return super.takeScreenshot();
			} else {
				this.viewHandler.switchToNativeView();
				String f = super.takeScreenshot();
				this.viewHandler.switchToWebView();
				return f;
			}
		} else {
			// For IOS use default
			return super.takeScreenshot();
		}
	}
}