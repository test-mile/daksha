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

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;

import com.testmile.daksha.core.value.StringValue;
import com.testmile.setu.agent.SetuAgentConfig;
import com.testmile.setu.agent.guiauto.core.automator.SeleniumGuiAutomator;
import com.testmile.trishanku.tpi.enums.Browser;
import com.testmile.trishanku.tpi.enums.SetuOption;

public class SeleniumBuilder extends GuiAutomatorBuilder{
	private Capabilities capabilities = null;
	private Browser browser;
	private WebDriver driver = null;
	
	public SeleniumBuilder(SetuAgentConfig config) throws Exception{
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
		
		browser = getConfig().getBrowser();
		
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
			if (StringValue.isSet(browserBinPath)) {
				fOptions.setBinary(browserBinPath);
			}
			driver = new FirefoxDriver();
			break;
		case CHROME:
			ChromeOptions coptions = new ChromeOptions();
			if (StringValue.isSet(browserBinPath)) {
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

	public SeleniumGuiAutomator build() throws Exception{
		return new SeleniumGuiAutomator(driver, getConfig());
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
