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
package daksha.core.leaping.automator.selenium;

import java.awt.Toolkit;
import java.io.File;
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
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.core.batteries.config.Batteries;
import daksha.core.enums.BatteriesPropertyType;
import daksha.core.leaping.automator.AbstractGuiAutomator;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.element.selenium.DefaultSeleniumElementProxy;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.enums.UiAutomatorPropertyType;
import daksha.core.leaping.enums.UiDriverEngine;
import daksha.core.leaping.enums.WebIdentifyBy;
import daksha.core.leaping.identifier.Locator;
import daksha.core.leaping.identifier.selenium.SeleniumIdentifier;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.core.leaping.identifier.Identifier;
import daksha.tpi.enums.Browser;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.enums.GuiElementType;
import daksha.tpi.sysauto.utils.FileSystemUtils;

public class SeleniumWebUiDriver extends BaseSeleniumWebUiDriver<WebDriver,WebElement>{
	
	public SeleniumWebUiDriver(ElementLoaderType loaderType) throws Exception{
		super(UiDriverEngine.WEBDRIVER, UiAutomationContext.PC_WEB, loaderType);
		this.setIdentifier(new SeleniumIdentifier(this));
	}
		
	public SeleniumWebUiDriver() throws Exception{
		this(ElementLoaderType.AUTOMATOR);
	}
	
}
