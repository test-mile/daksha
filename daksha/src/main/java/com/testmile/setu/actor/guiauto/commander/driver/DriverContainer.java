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

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;

import com.testmile.setu.actor.guiauto.adapter.driver.SetuDriverConfig;
import com.testmile.setu.actor.guiauto.core.GuiMultiElement;
import com.testmile.trishanku.tpi.enums.SetuOption;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class DriverContainer<T,E> {
	private T tool;
	private SetuDriverConfig config;
	
	protected DriverContainer(T tool, SetuDriverConfig config) throws Exception {
		this.tool = tool;
		this.config = config;
	}
	
	public T getTool() {
		return this.tool;
	}
	
	public abstract WebDriver asWebDriver() throws Exception;
	
	public abstract AppiumDriver<MobileElement> asAppiumDriver() throws Exception;
	
	protected SetuDriverConfig getConfig() {
		return this.config;
	}
	
	public static SeleniumContainer Selenium(SetuDriverConfig config) throws Exception {
		return SeleniumContainer.container(config);
	}
	
	public static AppiumContainer Appium(SetuDriverConfig config) throws Exception {
		return AppiumContainer.container(config);
	}
	
	public abstract DriverElementContainer<T,E> findElement(String by, String value) throws Exception;
	public abstract GuiMultiElement<T,E> findElements(String by, String value) throws Exception;
}