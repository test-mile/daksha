/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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
package daksha.core.leaping.automator.appium;

import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.Daksha;
import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.enums.BatteriesPropertyType;
import daksha.core.leaping.automator.AbstractGuiAutomator;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.selenium.DefaultSeleniumElementProxy;
import daksha.core.leaping.enums.AppiumMobilePlatformType;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.UiAutomatorPropertyType;
import daksha.core.leaping.enums.UiDriverEngine;
import daksha.core.leaping.enums.WebIdentifyBy;
import daksha.core.leaping.identifier.Locator;
import daksha.core.leaping.identifier.appium.AppiumIdentifier;
import daksha.core.leaping.identifier.selenium.SeleniumIdentifier;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.core.leaping.identifier.Identifier;
import daksha.core.leaping.automator.selenium.BaseSeleniumWebUiDriver;
import daksha.core.leaping.automator.selenium.SeleniumWebUiDriver;
import daksha.tpi.enums.Browser;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.sysauto.utils.FileSystemUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BaseAppiumUiDriver extends BaseSeleniumWebUiDriver<AppiumDriver<MobileElement>,MobileElement>{
	private AppiumMobilePlatformType platformType = null;
	private String appPath = null;
	
	public BaseAppiumUiDriver(UiAutomationContext context, ElementLoaderType loaderType) throws Exception{
		super(UiDriverEngine.APPIUM, context, loaderType);
		this.setIdentifier(new AppiumIdentifier(this));
	}
		
	public BaseAppiumUiDriver(UiAutomationContext context) throws Exception{
		this(context, ElementLoaderType.AUTOMATOR);
	}
	
	@Override
	public void load() throws Exception{
		AppiumDriver<MobileElement> driver = null;
		URL hubUrl = new URL(
				String.format(
						Batteries.value(UiAutomatorPropertyType.APPIUM_HUB_URL).asString(),
						Batteries.value(UiAutomatorPropertyType.APPIUM_HUB_HOST).asString(),
						Batteries.value(UiAutomatorPropertyType.APPIUM_HUB_PORT).asString()
						)
				);
		try{
			switch(getPlatformType()){
			case ANDROID: driver = new AndroidDriver<MobileElement>(hubUrl, capabilities); break;
			case IOS: driver = new IOSDriver<MobileElement>(hubUrl, capabilities); break;
			}
	
		}catch (UnreachableBrowserException e){
			throwUnreachableBrowserException(getPlatformType(), e);
		}
		this.setDriver(driver);
		this.setWaiter(new WebDriverWait(this.getUnderlyingEngine(), this.getWaitTime()));		
	}
	
	private void throwUnreachableBrowserException(AppiumMobilePlatformType platformType, Throwable e) throws Exception {
		throw new Problem(
				"Automator",
				this.getName(),
				"Constructor",
				Daksha.problem.APPIUM_UNREACHABLE_BROWSER,
				"Unreachable Appium Browser for " + Daksha.getAppiumPlatformString(platformType),
				e
				);
	}
	
	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}
	
	private AppiumMobilePlatformType getPlatformType() {
		return platformType;
	}
	
	protected AppiumDriver<MobileElement> getUnderlyingEngine() {
		return (AppiumDriver<MobileElement>) getUiDriverEngine();
	}

	public void setPlatformType(AppiumMobilePlatformType platformType) {
		this.platformType = platformType;
	}
	
	private void NOTEXPOSED_startService() {
		//		AppiumDriverLocalService service;
		//		String nodeLoc ="/Applications/Appium.app/Contents/Resources/node/bin/node";
		//		String appiumLoc ="/Applications/Appium.app/Contents/Resources/node_modules/appium/bin/appium.js";
		//		service = AppiumDriverLocalService
		//			.buildService
		//			(
		//			new AppiumServiceBuilder()
		//				.usingDriverExecutable(new File(nodeLoc))
		//				.withAppiumJS(new File(appiumLoc))
		//			);
		//		service.start();
	}
}
