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

import java.util.List;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.testmile.setu.actor.guiauto.adapter.GuiAutomatorBuilder;
import com.testmile.setu.actor.guiauto.core.GuiMultiElement;
import com.testmile.trishanku.tpi.enums.BrowserName;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.setu.actor.SetuActorConfig;
import com.testmile.trishanku.tpi.value.AnyRefValue;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class SeleniumContainer extends DriverContainer<WebDriver,WebElement>{

	protected SeleniumContainer(WebDriver tool, SetuActorConfig config) throws Exception {
		super(tool, config);
	}
	
	public static SeleniumContainer container(SetuActorConfig config) throws Exception {
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
	private Capabilities capabilities = null;
	private BrowserName browser;
	private WebDriver driver = null;
	
	public SeleniumBuilder(SetuActorConfig config) throws Exception{
		super(config);
		createCapabilities();
		load();
	}
	
	private void createCapabilities() throws Exception {
		MutableCapabilities browserCaps = new MutableCapabilities();
		MutableCapabilities otherCaps = new MutableCapabilities();
//		for(String cap: caps.keySet()) {
//			otherCaps.setCapability(cap, caps.get(cap));
//		}
		
		browser = getConfig().getBrowserName();
		
		switch (browser){
		case FIREFOX:
			setFirefoxCaps(browserCaps);
			break;
		case CHROME:
			setChromeCaps(browserCaps);
			break;
		case SAFARI:
			setSafariCaps(browserCaps);
			break;
		}
		
		browserCaps.merge(otherCaps);
		capabilities = browserCaps;
	}
	
	public void load() throws Exception{
		setDriverPath();
		driver = null;
		String browserBinPath = getConfig().value(SetuOption.BROWSER_BIN_PATH).asString();
		switch (this.browser){
		case FIREFOX:
			FirefoxOptions fOptions = new FirefoxOptions(capabilities);
			if (AnyRefValue.isSet(browserBinPath)) {
				fOptions.setBinary(browserBinPath);
			}
			driver = new FirefoxDriver();
			break;
		case CHROME:
			ChromeOptions coptions = new ChromeOptions();
			if (AnyRefValue.isSet(browserBinPath)) {
				coptions.setBinary(browserBinPath);
			}
			coptions.merge(capabilities);
			driver = new ChromeDriver(coptions);
			break;
		case SAFARI:
			SafariOptions sOptions = new SafariOptions();
			sOptions.merge(capabilities);
			driver = new SafariDriver(sOptions);
			break;
		}

	}

	public WebDriver build() throws Exception{
		return driver;
	}

	private MutableCapabilities getFireFoxCapabilitiesSkeleton() { 
		return DesiredCapabilities.firefox();
	}

	private MutableCapabilities getChromeCapabilitiesSkeleton() {
		return DesiredCapabilities.chrome();
	}

	private MutableCapabilities getSafariCapabilitiesSkeleton() {
		return DesiredCapabilities.safari();
	}
	
	private void setBrowserVersion(MutableCapabilities browserCaps) throws Exception{
		browserCaps.setCapability(CapabilityType.BROWSER_VERSION, getConfig().value(SetuOption.BROWSER_VERSION).asString());
	}
	
	private void setFirefoxCaps(MutableCapabilities browserCaps) throws Exception {
		browserCaps = getFireFoxCapabilitiesSkeleton();
		setBrowserVersion(browserCaps);
		FirefoxProfile profile = new FirefoxProfile();
		//profile..setEnableNativeEvents(true);
		browserCaps.setCapability(FirefoxDriver.PROFILE, profile);
	}

	private void setDriverPath() throws Exception {
		String driverPath = getConfig().value(SetuOption.SELENIUM_DRIVER_PATH).asString();
		System.setProperty(getConfig().value(SetuOption.SELENIUM_DRIVER_PROP).asString(), driverPath);
	}
	
	private void setChromeCaps(MutableCapabilities browserCaps) throws Exception {
		browserCaps = getChromeCapabilitiesSkeleton();
		setBrowserVersion(browserCaps);
	}

	private void setSafariCaps(MutableCapabilities browserCaps) throws Exception {
		browserCaps = getSafariCapabilitiesSkeleton();
		setBrowserVersion(browserCaps);
	}
}