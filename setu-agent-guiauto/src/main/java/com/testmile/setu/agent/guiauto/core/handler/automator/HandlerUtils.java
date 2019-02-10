package com.testmile.setu.agent.guiauto.core.handler.automator;

import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.trishanku.tpi.guiauto.enums.MobileView;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class HandlerUtils {
	
	public static boolean isWebView(AppiumDriver<MobileElement> driver) {
		return driver.getContext().contains(MobileView.WEBVIEW.toString());
	}

	public static boolean isNativeView(AppiumDriver<MobileElement> driver) {
		return driver.getContext().contains(MobileView.NATIVE_APP.toString());
	}

	public static boolean isWebContextOrWebView(GuiAutomationContext autoContext, AppiumDriver<MobileElement> driver) {
		if ((GuiAutomationContext.isAnyWebContext(autoContext) || HandlerUtils.isWebView(driver))){
			return true;
		}
		
		return false;
	}
	
	public static void throwUnsupportedComponentExceptionForNullObject(Object obj, String name, String componentName) throws Exception {
		if (obj == null) {
			throw new Exception(String.format("%s does not contain %s component.", name, componentName));
		}
	}
	
}
