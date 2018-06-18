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

import java.net.URL;

import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.Daksha;
import daksha.ErrorType;
import daksha.core.batteries.config.TestContext;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.automator.selenium.BaseSeleniumWebUiDriver;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.OSType;
import daksha.core.leaping.enums.UiDriverEngine;
import daksha.core.leaping.identifier.appium.AppiumIdentifier;
import daksha.tpi.enums.DakshaOption;
import daksha.tpi.leaping.enums.GuiAutomationContext;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BaseAppiumUiDriver extends BaseSeleniumWebUiDriver<AppiumDriver<MobileElement>,MobileElement>{
	private String appPath = null;
	
	public BaseAppiumUiDriver(TestContext testContext, GuiAutomationContext automatorContext, ElementLoaderType loaderType) throws Exception{
		super(testContext, UiDriverEngine.APPIUM, automatorContext, loaderType);
		this.setIdentifier(new AppiumIdentifier(this));
	}
		
	public BaseAppiumUiDriver(TestContext testContext, GuiAutomationContext automatorContext) throws Exception{
		this(testContext, automatorContext, ElementLoaderType.AUTOMATOR);
	}
	
	@Override
	public void load() throws Exception{
		AppiumDriver<MobileElement> driver = null;
		URL hubUrl = new URL(
				String.format(
						this.getTestContext().getConfig().value(DakshaOption.APPIUM_HUB_URL).asString(),
						this.getTestContext().getConfig().value(DakshaOption.APPIUM_HUB_HOST).asString(),
						this.getTestContext().getConfig().value(DakshaOption.APPIUM_HUB_PORT).asString()
						)
				);
		try{
			switch(getOSType()){
			case ANDROID: driver = new AndroidDriver<MobileElement>(hubUrl, capabilities); break;
			case IOS: driver = new IOSDriver<MobileElement>(hubUrl, capabilities); break;
			}
	
		}catch (UnreachableBrowserException e){
			throwUnreachableBrowserException(getOSType(), e);
		}
		this.setDriver(driver);
		this.setWaiter(new WebDriverWait(this.getUnderlyingEngine(), this.getWaitTime()));		
	}
	
	private void throwUnreachableBrowserException(OSType platformType, Throwable e) throws Exception {
		throw new Problem(
				"Automator",
				this.getName(),
				"Constructor",
				ErrorType.APPIUM_UNREACHABLE_BROWSER,
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
	
	protected AppiumDriver<MobileElement> getUnderlyingEngine() {
		return (AppiumDriver<MobileElement>) getUiDriverEngine();
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
