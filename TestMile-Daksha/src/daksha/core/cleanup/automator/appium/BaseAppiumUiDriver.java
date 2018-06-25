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
package daksha.core.cleanup.automator.appium;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.Daksha;
import daksha.core.batteries.config.TestContext;
import daksha.core.cleanup.automator.selenium.BaseSeleniumWebUiDriver;
import daksha.core.cleanup.enums.Direction;
import daksha.core.cleanup.enums.ElementLoaderType;
import daksha.core.cleanup.enums.MobileView;
import daksha.core.cleanup.enums.OSType;
import daksha.core.cleanup.enums.UiDriverEngine;
import daksha.core.cleanup.picker.appium.AppiumElementPicker;
import daksha.core.problem.ErrorType;
import daksha.core.problem.Problem;
import daksha.tpi.cleanup.enums.UiAutomationContext;
import daksha.tpi.enums.DakshaOption;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

public class BaseAppiumUiDriver extends BaseSeleniumWebUiDriver<AppiumDriver<MobileElement>,MobileElement>{
	private String appPath = null;
	private float swipeTop;
	private float swipeBottom;
	private int swipeMaxWait;
	
	public BaseAppiumUiDriver(TestContext testContext, UiAutomationContext automatorContext, ElementLoaderType loaderType) throws Exception{
		super(testContext, UiDriverEngine.APPIUM, automatorContext, loaderType);
		swipeTop = testContext.getConfig().value(DakshaOption.UIAUTO_SWIPE_TOP).asFloat();
		swipeBottom = testContext.getConfig().value(DakshaOption.UIAUTO_SWIPE_BOTTOM).asFloat();
		swipeMaxWait = testContext.getConfig().value(DakshaOption.UIAUTO_SWIPE_MAX_WAIT).asInt();
	}
		
	public BaseAppiumUiDriver(TestContext testContext, UiAutomationContext automatorContext) throws Exception{
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
		this.setPicker(new AppiumElementPicker(this));
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
	
	protected boolean isWebView() {
		return getUnderlyingEngine().getContext().contains(MobileView.WEBVIEW.toString());
	}

	protected boolean isNativeView() {
		return getUnderlyingEngine().getContext().contains(MobileView.NATIVE_APP.toString());
	}

	private void validateSwipeSupport() throws Exception {
		if ((this.getAutomatorContext() == UiAutomationContext.MOBILE_WEB) || isWebView()){
			throw new Exception("Swipe actions are not supported for Web View.");
		}
	}

	protected void validateScrollSupport() throws Exception {
		if ((this.getAutomatorContext() == UiAutomationContext.MOBILE_NATIVE) || isNativeView()){
			throw new Exception("Scroll actions are not supported for Native View.");
		}
	}
	
	private void swipe(Direction direction, int count, float startFraction, float endFraction) throws Exception {
		validateSwipeSupport();
		Dimension size = this.getUnderlyingEngine().manage().window().getSize();
		int starty = (int) (size.height * startFraction);
		int endy = (int) (size.height * endFraction);
		int width = size.width / 2;
		for (int i = 0; i < count; i++) {
			new TouchAction(this.getUnderlyingEngine()).press(width, starty).waitAction(Duration.ofSeconds(swipeMaxWait))
					.moveTo(width, endy).release().perform();
		}
	}

	@Override
	public void swipeUp(int count) throws Exception {
		swipe(Direction.UP, count, swipeTop, swipeBottom);
	}

	@Override
	public void swipeUp() throws Exception {
		swipeUp(1);
	}

	@Override
	public void swipeDown(int count) throws Exception {
		swipe(Direction.DOWN, count, swipeBottom, swipeTop);
	}

	@Override
	public void swipeDown() throws Exception {
		swipeDown(1);
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
