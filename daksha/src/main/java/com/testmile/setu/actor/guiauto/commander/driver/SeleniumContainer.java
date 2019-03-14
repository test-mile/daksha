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

package com.testmile.setu.actor.guiauto.commander.driver;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.testmile.setu.actor.guiauto.adapter.GuiAutomatorBuilder;
import com.testmile.setu.actor.guiauto.adapter.driver.SetuDriverConfig;
import com.testmile.setu.actor.guiauto.adapter.driver.SetuGuiAutoActorOption;
import com.testmile.setu.actor.guiauto.core.GuiMultiElement;
import com.testmile.trishanku.tpi.value.AnyRefValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SeleniumContainer extends DriverContainer<WebDriver,WebElement>{

	protected SeleniumContainer(WebDriver tool, SetuDriverConfig config) throws Exception {
		super(tool, config);
	}
	
	public static SeleniumContainer container(SetuDriverConfig config) throws Exception {
		SeleniumBuilder builder = new SeleniumBuilder(config);
		WebDriver driver = builder.build();
		return new SeleniumContainer(driver, config);
	}
	
	public WebDriver asWebDriver() {
		return this.getTool();
	}
	
	public AppiumDriver<MobileElement> asAppiumDriver() throws Exception{
		throw new Exception("Selenium Webdriver can not be be cast as AppiumDriver");
	}

	@Override
	public GuiMultiElement<WebDriver, WebElement> findElements(String by, String value) throws Exception {
		 List<WebElement> elements = this.getTool().findElements(DriverElementContainer.getSeleniumByLocator(by, value));
		 return DriverElementContainer.seleniumMultiElement(this.getTool(), elements, this.getConfig());
	}
	
	@Override
	public DriverElementContainer<WebDriver, WebElement> findElement(String by, String value) throws Exception {
		 WebElement element = this.getTool().findElement(DriverElementContainer.getSeleniumByLocator(by, value));
		 return DriverElementContainer.seleniumElement(this.getTool(), element, this.getConfig());
	}
	
}

class SeleniumBuilder extends GuiAutomatorBuilder{
	public SeleniumBuilder(SetuDriverConfig config) throws Exception{
		super(config);
	}
	
	public WebDriver build() throws Exception{
		setDriverPath();
		String browserBinPath = getConfig().value(SetuGuiAutoActorOption.BROWSER_BIN_PATH).asString();
		
		switch (this.getConfig().getBrowserName()){
		case FIREFOX:
			return this.createFirefox(browserBinPath);
		case CHROME:
			return this.createChrome(browserBinPath);
		case SAFARI:
			return this.createSafari();
		default:
			throw new Exception(String.format("Unsupported Browser: %s", this.getConfig().getBrowserName()));
		}
	}
	
	private void setDriverPath() throws Exception {
		String driverPath = getConfig().value(SetuGuiAutoActorOption.SELENIUM_DRIVER_PATH).asString();
		System.setProperty(getConfig().value(SetuGuiAutoActorOption.SELENIUM_DRIVER_PROP).asString(), driverPath);
	}
	
	public WebDriver createChrome(String browserBinPath) throws Exception {
		ChromeOptions options = new ChromeOptions();
		if (AnyRefValue.isSet(browserBinPath)) {
			options.setBinary(browserBinPath);
		}		
		
		Map<String, Object> caps= DesiredCapabilities.chrome().asMap();
		
		for(String cap: caps.keySet()) {
			options.setCapability(cap, caps.get(cap));
		}

		for(String cap: getConfig().getDriverCapabilities().keySet()) {
			options.setCapability(cap, getConfig().getDriverCapabilities().get(cap));
		}
		
		if (getConfig().getBrowserPreferences() != null) {
			options.setExperimentalOption("prefs", getConfig().getBrowserPreferences());
		}
		
		if (getConfig().getBrowserArgs() != null) {
			options.addArguments(getConfig().getBrowserArgs());
		}
		
		if (getConfig().getBrowserExtensions() != null) {
			options.addExtensions(getConfig().getBrowserExtensions());
		}
		
		if (this.getConfig().isProxyEnabled()) {
			System.out.println("Proxy enabled.");
			Proxy proxy = new Proxy();
			String proxyString = this.getConfig().value(SetuGuiAutoActorOption.BROWSER_PROXY_HOST).asString() 
					+ ":" + 
					this.getConfig().value(SetuGuiAutoActorOption.BROWSER_PROXY_PORT).asString();
			proxy.setHttpProxy(proxyString);
			proxy.setSslProxy(proxyString);
			options.setCapability("proxy", proxy);
		}
		
		return new ChromeDriver(options);
	}
	
	
	public WebDriver createFirefox(String browserBinPath) throws Exception {
		FirefoxOptions options = new FirefoxOptions();
		if (AnyRefValue.isSet(browserBinPath)) {
			options.setBinary(browserBinPath);
		}
		
		for(String cap: getConfig().getDriverCapabilities().keySet()) {
			options.setCapability(cap, getConfig().getDriverCapabilities().get(cap));
		}
		
		for(String pref: getConfig().getBrowserPreferences().keySet()) {
			options.addPreference(pref, getConfig().getDriverCapabilities().get(pref));
		}

		options.addArguments(getConfig().getBrowserArgs());
		
		FirefoxProfile profile = new FirefoxProfile();
		
		for(File ext: getConfig().getBrowserExtensions()) {
			profile.addExtension(ext);
		}
		
		if (this.getConfig().isProxyEnabled()) {
			Proxy proxy = new Proxy();
			String proxyHost = this.getConfig().value(SetuGuiAutoActorOption.BROWSER_PROXY_HOST).asString();
			int proxyPort = this.getConfig().value(SetuGuiAutoActorOption.BROWSER_PROXY_PORT).asInt();
			options.setCapability("proxy", proxy);
			
			profile.setPreference("network.proxy.type", 1);
		    profile.setPreference("network.proxy.http", proxyHost);
		    profile.setPreference("network.proxy.http_port", proxyPort);
		    profile.setPreference("network.proxy.ssl", proxyHost);
		    profile.setPreference("network.proxy.ssl_port", proxyPort);
		}
		
		options.setCapability(FirefoxDriver.PROFILE, profile);
	
		return new FirefoxDriver(options);
	}
	
	public WebDriver createSafari() {
		SafariOptions options = new SafariOptions();
		
		for(String cap: getConfig().getDriverCapabilities().keySet()) {
			options.setCapability(cap, getConfig().getDriverCapabilities().get(cap));
		}
	
		return new SafariDriver(options);
	}
}