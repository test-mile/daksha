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

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.actor.guiauto.core.GuiMultiElement;
import com.testmile.trishanku.tpi.setu.actor.SetuActorConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;

public abstract class DriverElementContainer<T,E> {
	private T tool;
	private E element;
	private SetuActorConfig config;
	
	protected DriverElementContainer(T tool, E element, SetuActorConfig config) throws Exception {
		this.tool = tool;
		this.element = element;
		this.config = config;
	}
	
	public T getTool() {
		return this.tool;
	}
	
	public E getElement() {
		return this.element;
	}
	
	protected SetuActorConfig getConfig() {
		return this.config;
	}
	
	public abstract WebElement asWebElement() throws Exception;
	
	public abstract MobileElement asMobileElement() throws Exception;
	
	public static DriverElementContainer<WebDriver, WebElement> seleniumElement(WebDriver driver, WebElement element, SetuActorConfig config) throws Exception {
		return new SeleniumElementDispatcher(driver, element, config);
	}
	
	public static DriverElementContainer<AppiumDriver<MobileElement>, MobileElement> appiumElement(AppiumDriver<MobileElement> driver, MobileElement element, SetuActorConfig config) throws Exception {
		return new AppiumElementDispatcher(driver, element, config);
	}
	
	public static GuiMultiElement<WebDriver, WebElement> seleniumMultiElement(WebDriver driver, List<WebElement> rawElements, SetuActorConfig config) throws Exception {
		List<DriverElementContainer<WebDriver,WebElement>> elements = new ArrayList<DriverElementContainer<WebDriver,WebElement>>();
		for (WebElement element: rawElements) {
			elements.add(seleniumElement(driver, element, config));
		}
		return new GuiMultiElement<WebDriver,WebElement>(elements);
	}
	
	public static GuiMultiElement<AppiumDriver<MobileElement>, MobileElement> appiumMultiElement(AppiumDriver<MobileElement> driver, List<MobileElement> rawElements, SetuActorConfig config) throws Exception {
		List<DriverElementContainer<AppiumDriver<MobileElement>,MobileElement>> elements = new ArrayList<DriverElementContainer<AppiumDriver<MobileElement>,MobileElement>>();
		for (MobileElement element: rawElements) {
			elements.add(appiumElement(driver, element, config));
		}
		return new GuiMultiElement<AppiumDriver<MobileElement>, MobileElement>(elements);
	}
	
	public static By getSeleniumByLocator(String by, String value) throws Exception{
		switch(by.toUpperCase()) {
		case "ID": return By.id(value);
		case "NAME": return By.name(value);
		case "TAG_NAME": return By.tagName(value);
		case "CLASS_NAME": return By.className(value);
		case "LINK_TEXT": return By.linkText(value);
		case "PARTIAL_LINK_TEXT": return By.partialLinkText(value);
		case "CSS_SELECTOR": return By.cssSelector(value);
		case "XPATH": return By.xpath(value);
		default:
			throw new Exception(String.format("Unsupported identifier for %s: Type:%s Value:%s",
					DriverElementContainer.class.getSimpleName(), by, value)
			);		
		}
	}
	
	public static By getAppiumByLocator(String by, String value) throws Exception{
		switch(by.toUpperCase()) {
		case "ACCESSIBILITY_ID": return MobileBy.AccessibilityId(value);
		default:
			return getSeleniumByLocator(by, value);	
		}
	}
	
	public abstract DriverElementContainer<T,E> findElement(String by, String value) throws Exception;
	public abstract GuiMultiElement<T,E> findElements(String by, String value) throws Exception;
}

class SeleniumElementDispatcher extends DriverElementContainer<WebDriver,WebElement>{

	protected SeleniumElementDispatcher(WebDriver tool, WebElement element, SetuActorConfig config) throws Exception {
		super(tool, element, config);
	}
	
	public WebElement asWebElement() {
		return this.getElement();
	}
	
	public MobileElement asMobileElement() throws Exception{
		throw new Exception("Selenium WebElement can not be be cast as Appium MobileElement");
	}

	@Override
	public GuiMultiElement<WebDriver, WebElement> findElements(String by, String value) throws Exception {
		 List<WebElement> elements = this.getElement().findElements(DriverElementContainer.getSeleniumByLocator(by, value));
		 return seleniumMultiElement(this.getTool(), elements, this.getConfig());
	}
	
	@Override
	public DriverElementContainer<WebDriver, WebElement> findElement(String by, String value) throws Exception {
		 WebElement element = this.getElement().findElement(DriverElementContainer.getSeleniumByLocator(by, value));
		 return seleniumElement(this.getTool(), element, this.getConfig());
	}
}

class AppiumElementDispatcher extends DriverElementContainer<AppiumDriver<MobileElement>,MobileElement>{

	protected AppiumElementDispatcher(AppiumDriver<MobileElement> tool, MobileElement element, SetuActorConfig config) throws Exception {
		super(tool, element, config);
	}
	
	public WebElement asWebElement() {
		return this.getElement();
	}
	
	public MobileElement asMobileElement() throws Exception{
		return this.getElement();
	}
	
	@Override
	public GuiMultiElement<AppiumDriver<MobileElement>, MobileElement> findElements(String by, String value) throws Exception {
		 List<MobileElement> elements = this.getElement().findElements(DriverElementContainer.getAppiumByLocator(by, value));
		 return appiumMultiElement(this.getTool(), elements, this.getConfig());
	}
	
	@Override
	public DriverElementContainer<AppiumDriver<MobileElement>, MobileElement> findElement(String by, String value) throws Exception {
		MobileElement element = this.getElement().findElement(DriverElementContainer.getAppiumByLocator(by, value));
		 return appiumElement(this.getTool(), element, this.getConfig());
	}
}
