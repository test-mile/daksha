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
package com.testmile.setu.actor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.testmile.setu.actor.guiauto.core.backup.AppiumDriverServerLauncher;
import com.testmile.trishanku.tpi.enums.AppiumAndroidBrowserType;
import com.testmile.trishanku.tpi.enums.AppiumIosBrowserType;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.OSType;

public enum SetuActorSingleton {
	INSTANCE;
	private SetuActorConfig setuAgentConfig;
	private static AppiumDriverServerLauncher serverLauncher = null;
	private static Map<Browser, String> browserDriverMap = null;
	private static Map<Browser, String> browserSysPropNameMap = null;
	
	// Appium
	private static List<String> allowedAppiumPlatforms = new ArrayList<String>();;
	private static List<String> allowedAppiumAndroidBrowsers = new ArrayList<String>();;
	private static List<String> allowedAppiumIosBrowsers = new ArrayList<String>();

	public void init() throws Exception{
		serverLauncher = new AppiumDriverServerLauncher();
		
		/* Appium */
		for (OSType prop: OSType.class.getEnumConstants()){
			allowedAppiumPlatforms.add(prop.toString());
		}

		for (AppiumAndroidBrowserType prop: AppiumAndroidBrowserType.class.getEnumConstants()){
			allowedAppiumAndroidBrowsers.add(prop.toString());
		}

		for (AppiumIosBrowserType prop: AppiumIosBrowserType.class.getEnumConstants()){
			allowedAppiumIosBrowsers.add(prop.toString());
		}

		/* UI Automator */		
		browserDriverMap = new HashMap<Browser, String>();
		browserDriverMap.put(Browser.CHROME, "chromedriver");
		browserDriverMap.put(Browser.FIREFOX, "geckodriver");
		browserDriverMap.put(Browser.SAFARI, "safaridriver");
		
		browserSysPropNameMap = new HashMap<Browser, String>();
		browserSysPropNameMap.put(Browser.CHROME, "webdriver.chrome.driver");
		browserSysPropNameMap.put(Browser.FIREFOX, "webdriver.gecko.driver");
	}
	
	public AppiumDriverServerLauncher getDriverServerLauncher() throws Exception{
		return serverLauncher;
	}	
	
	/*
	 * Appium Inquiry Methods
	 */

	private List<String> getAllowedPlatformsForAppium(){
		return allowedAppiumPlatforms;
	}

	private List<String> getAllowedBrowsersForAppium(OSType platform) throws Exception{
		switch (platform){
		case ANDROID: return allowedAppiumAndroidBrowsers;
		case IOS: return allowedAppiumIosBrowsers;
		default: throw new Exception("Unknown platform: " + platform);
		}
	}

	public boolean isAllowedAppiumPlatform(String platformName){
		return getAllowedPlatformsForAppium().contains(platformName.toUpperCase());
	}

	public boolean isAllowedAppiumBrowser(OSType platform, String browserName) throws Exception{
		return getAllowedBrowsersForAppium(platform).contains(browserName.toUpperCase());
	}

	public String getAppiumPlatformString(OSType platform) throws Exception{
		switch(platform){
		case ANDROID: return "Android";
		case IOS: return "iOS";
		default: return platform.toString();
		}
	}

	public String getAppiumBrowserString(String rawName) throws Exception{
		// Need to fix
		return rawName;
	}
	
	public String getDriverName(Browser browser) throws Exception {
		try {
			return this.browserDriverMap.get(browser);
		} catch (Exception e) {
			throw new Exception(String.format("Browser [] does not have a driver.", browser));
		}
	}
	
	public boolean isSeleniumDriverPathNeeded(Browser browser) throws Exception {
		return browserSysPropNameMap.containsKey(browser);
	}
	
	public String getSeleniumDriverPathSystemProperty(Browser browser) throws Exception {
		try {
			return browserSysPropNameMap.get(browser);
		} catch (Exception e) {
			throw new Exception(String.format("Browser [] does not have a driver path related System property.", browser));
		}
	}

	public SetuActorConfig getSetuAgentConfig() {
		return setuAgentConfig;
	}

	public void setSetuAgentConfig(SetuActorConfig config) {
		this.setuAgentConfig = config;
	}
}
