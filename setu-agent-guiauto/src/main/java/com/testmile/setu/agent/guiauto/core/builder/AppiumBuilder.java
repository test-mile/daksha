/*******************************************************************************
 * Copyright 2015-19 Test Mile Software Testing Pvt Ltd
 * 
 * Website: www.TestMile.com
 * Email: support [at] testmile.com
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/

package com.testmile.setu.agent.guiauto.core.builder;

import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.UnreachableBrowserException;

import com.testmile.daksha.core.problem.ErrorType;
import com.testmile.daksha.core.problem.Problem;
import com.testmile.daksha.tpi.enums.DakshaOption;
import com.testmile.daksha.tpi.guiauto.enums.GuiAutomationContext;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.SetuAgentGuiAutoSingleton;
import com.testmile.setu.agent.guiauto.core.automator.AppiumGuiAutomator;
import com.testmile.setu.agent.guiauto.core.launcher.appium.AppiumServer;
import com.testmile.trishanku.Trishanku;
import com.testmile.trishanku.tpi.guiauto.enums.OSType;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumBuilder extends GuiAutomatorBuilder{
	private Capabilities capabilities = null;
	private OSType platformType = null;
	private AppiumDriver<MobileElement> driver;
	
	public AppiumBuilder(SetuAgentConfig config) throws Exception{
		super(config);
		createCapabilities();
		load();
	}
		
	private void createCapabilities() throws Exception {
		MutableCapabilities appiumCaps = new MutableCapabilities();
		populateCommonCaps(appiumCaps);
		populateContextSpecificCaps(appiumCaps);
		this.capabilities = appiumCaps;
	}
	
	private void populateCommonCaps(MutableCapabilities caps) throws Exception {
		setProxy(caps);
		String platform = getConfig().value(DakshaOption.TESTRUN_TARGET_PLATFORM).asString();
		if (!SetuAgentGuiAutoSingleton.INSTANCE.isAllowedAppiumPlatform(platform)){
			throwUnsupportedPlatformException("constructor", platform);
		}
		this.platformType = OSType.valueOf(platform.toUpperCase());	
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, SetuAgentGuiAutoSingleton.INSTANCE.getAppiumPlatformString(this.platformType));
		caps.setCapability(MobileCapabilityType.PLATFORM_VERSION,  getConfig().value(DakshaOption.TESTRUN_TARGET_PLATFORM_VERSION).asString());
		caps.setCapability("newCommandTimeout", "60000");
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, getConfig().value(DakshaOption.MOBILE_DEVICE_NAME).asString());
		if (!getConfig().value(DakshaOption.MOBILE_DEVICE_UDID).isNull()){
			caps.setCapability(MobileCapabilityType.UDID, getConfig().value(DakshaOption.MOBILE_DEVICE_UDID).asString());
		}
	}
	
	private OSType getPlatform() {
		return this.platformType;
	}
		
	private void setHttpProxy(Proxy proxy, String proxyString) {
		proxy.setHttpProxy(proxyString);
	}

	private void setSslProxy(Proxy proxy, String proxyString) {
		proxy.setSslProxy(proxyString);
	}
	
	private void setProxy(MutableCapabilities capabilities) throws Exception {
		if (getConfig().value(DakshaOption.BROWSER_PROXY_ON).asBoolean()){
			Proxy proxy = new Proxy();
			String p = getConfig().value(DakshaOption.BROWSER_PROXY_HOST).asString() + ":" + getConfig().value(DakshaOption.BROWSER_PROXY_PORT).asString();
			setHttpProxy(proxy, p);
			setSslProxy(proxy, p);
			capabilities.setCapability("proxy", proxy);
		}		
	}
	
	private void populateContextSpecificCaps(MutableCapabilities capabilities) throws Exception {
		OSType platform = getPlatform();
		if (GuiAutomationContext.isMobileNativeContext(getAutomationContext())) {
			setMobileNativeCapabilities(platform, capabilities);
		} else if (GuiAutomationContext.isMobileWebContext(getAutomationContext())) {
			setMobileWebCapabilities(platform, capabilities);
		} else {
			throwUnsupportedAutomationContextException(getAutomationContext());
		}
	}
	
	public void load() throws Exception{
		AppiumDriver<MobileElement> driver = null;
		AppiumServer server = null;
		if (getConfig().value(DakshaOption.APPIUM_AUTO_LAUNCH).asBoolean() == true) {
			server = SetuAgentGuiAutoSingleton.INSTANCE.getDriverServerLauncher().startServer();
		} else {
			server = new AppiumServer(getConfig().value(DakshaOption.APPIUM_HUB_URL).asString());
		}
		URL hubUrl = server.getURL();
		try{
			switch(this.getPlatform()){
			case ANDROID: driver = new AndroidDriver<MobileElement>(hubUrl, capabilities); break;
			case IOS: driver = new IOSDriver<MobileElement>(hubUrl, capabilities); break;
		}
	
		}catch (UnreachableBrowserException e){
			throwUnreachableBrowserException(this.getPlatform(), e);
		}
	}
		
	public AppiumGuiAutomator build() throws Exception{
		return new AppiumGuiAutomator(driver, this.getConfig());
	}
	
	public void throwUnsupportedAutomationContextException(GuiAutomationContext context) throws Exception{
		throw new Problem(
				"UI Auto:Generator:Appium",
				"Appium Builder",
				"build",
				ErrorType.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
				String.format(
						ErrorType.FACTORY_AUTOMATOR_UNSUPPORTED_CONTEXT,
						Trishanku.getAutomationContextName(context))
			);		
	}
	
	private void setMobileNativeCapabilities(OSType platform, MutableCapabilities capabilities) throws Exception {		
		capabilities.setCapability(MobileCapabilityType.APP, getConfig().value(DakshaOption.MOBILE_APP_FILE_PATH).asString());
		capabilities.setCapability("appPackage", getConfig().value(DakshaOption.MOBILE_APP_PACKAGE).asString());
		capabilities.setCapability("appActivity", getConfig().value(DakshaOption.MOBILE_APP_ACTIVITY).asString());
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		
		if (platform == OSType.ANDROID) {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		} else {
			capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
		}
	}

	private void setMobileWebCapabilities(OSType platform, MutableCapabilities capabilities) throws Exception {
		String browser = getConfig().value(DakshaOption.BROWSER_NAME).asString();
		if (!SetuAgentGuiAutoSingleton.INSTANCE.isAllowedAppiumBrowser(platform, browser)){
			throwUnsupportedBrowserException("setMobileCapabilities", platform, browser);
		}
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, SetuAgentGuiAutoSingleton.INSTANCE.getAppiumBrowserString(browser));
		
		if (platform == OSType.ANDROID) {
			capabilities.setCapability("unicodeKeyboard", true);
			capabilities.setCapability("resetKeyboard", true);
		}
	}
	
	/**********************************************************************************
	**					EXCEPTIONS											
	**********************************************************************************/
	
	protected void throwAppiumAutomatorException(String action, String code, String message) throws Exception {
		throw new Problem(
				"UI Auto:Generator:Appium",
				this.getClass().getSimpleName(),
				action,
				code,
				message
				);		
	}
	
	protected void throwUnsupportedPlatformException(String methodName, String platform) throws Exception {
		throwAppiumAutomatorException(
				methodName,
				ErrorType.APPIUM_UNSUPPORTED_PLATFORM,
				String.format(
						ErrorType.APPIUM_UNSUPPORTED_PLATFORM,
						platform
						)
				);
	}

	protected void throwUnsupportedBrowserException(String methodName, OSType platform, String browser) throws Exception {
		throwAppiumAutomatorException(
				methodName,
				ErrorType.APPIUM_UNSUPPORTED_BROWSER,
				String.format(
						ErrorType.APPIUM_UNSUPPORTED_BROWSER,
						browser,
						SetuAgentGuiAutoSingleton.INSTANCE.getAppiumPlatformString(platform)
						)
				);
	}
	
	private void throwUnreachableBrowserException(OSType platformType, Throwable e) throws Exception {
		throw new Problem(
				"Automator",
				"Appium Builder",
				"Constructor",
				ErrorType.APPIUM_UNREACHABLE_BROWSER,
				"Unreachable Appium Browser for " + SetuAgentGuiAutoSingleton.INSTANCE.getAppiumPlatformString(platformType),
				e
				);
	}

}
