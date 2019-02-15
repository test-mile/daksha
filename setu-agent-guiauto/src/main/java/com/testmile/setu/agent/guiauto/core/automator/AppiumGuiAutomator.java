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
package com.testmile.setu.agent.guiauto.core.automator;

import java.util.concurrent.TimeUnit;

import com.testmile.daksha.tpi.enums.Browser;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.handler.automator.appium.AppiumElementFinder;
import com.testmile.setu.agent.guiauto.core.handler.automator.appium.AppiumSwiper;
import com.testmile.setu.agent.guiauto.core.handler.automator.appium.AppiumViewHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumAlertHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumBrowserHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumFrameHandler;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumJSExecutor;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumScreenshoter;
import com.testmile.setu.agent.guiauto.core.handler.automator.selenium.SeleniumScroller;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumGuiAutomator extends BaseGuiAutomator{
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumGuiAutomator(AppiumDriver<MobileElement> driver, SetuAgentConfig config) throws Exception{
		super(config);
		this.appiumDriver = driver;
		initComponents();
	}
	
	private void initComponents() throws Exception{
		if(getConfig().getBrowser() != Browser.SAFARI){
			this.appiumDriver.manage().timeouts().pageLoadTimeout(this.getConfig().getMaxWaitTime(), TimeUnit.SECONDS);
		}
		
		this.setAlertHandler(new SeleniumAlertHandler(this.appiumDriver, this.getConfig()));
		this.setBrowserHandler(new SeleniumBrowserHandler(this.appiumDriver, this.getConfig()));
		this.setFrameHandler(new SeleniumFrameHandler(this.appiumDriver, this.getConfig()));
		this.setViewHandler(new AppiumViewHandler(this.appiumDriver, this.getConfig()));
		this.setJsExecutor(new SeleniumJSExecutor(this.appiumDriver, this.getConfig()));
		this.setScreenshoter(new SeleniumScreenshoter(this.appiumDriver, this.getConfig()));
		this.setScroller(new SeleniumScroller(this.appiumDriver, this.getConfig()));
		this.setSwiper(new AppiumSwiper(this.appiumDriver, this.getConfig()));
		this.setElementFinder(new AppiumElementFinder(this.appiumDriver, getConfig()));
	}

	@Override
	public void quit() throws Exception {
		appiumDriver.close();
	}	
	
}

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
