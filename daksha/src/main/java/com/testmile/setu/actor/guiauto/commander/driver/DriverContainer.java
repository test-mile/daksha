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

import com.testmile.setu.actor.guiauto.core.GuiMultiElement;
import com.testmile.trishanku.tpi.enums.SetuOption;
import com.testmile.trishanku.tpi.setu.actor.SetuActorConfig;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public abstract class DriverContainer<T,E> {
	private T tool;
	private SetuActorConfig config;
	
	protected DriverContainer(T tool, SetuActorConfig config) throws Exception {
		this.tool = tool;
		this.config = config;
	}
	
	public T getTool() {
		return this.tool;
	}
	
	public abstract WebDriver asWebDriver() throws Exception;
	
	public abstract AppiumDriver<MobileElement> asAppiumDriver() throws Exception;
	
	protected SetuActorConfig getConfig() {
		return this.config;
	}
	
	public static SeleniumContainer Selenium(SetuActorConfig config) throws Exception {
		return SeleniumContainer.container(config);
	}
	
	public static AppiumContainer Appium(SetuActorConfig config) throws Exception {
		return AppiumContainer.container(config);
	}
	
	public abstract DriverElementContainer<T,E> findElement(String by, String value) throws Exception;
	public abstract GuiMultiElement<T,E> findElements(String by, String value) throws Exception;
}

class ProxyHandler {
	private SetuActorConfig config;
	
	public ProxyHandler(SetuActorConfig config) throws Exception {
		this.config = config;
	}
	
	public void configureProxy(MutableCapabilities caps) throws Exception {
		if (config.value(SetuOption.BROWSER_PROXY_ON).asBoolean()){
			Proxy proxy = new Proxy();
			String p = config.value(SetuOption.BROWSER_PROXY_HOST).asString() + ":" + config.value(SetuOption.BROWSER_PROXY_PORT).asString();
			setHttpProxy(proxy, p);
			setSslProxy(proxy, p);
			caps.setCapability("proxy", proxy);
		}
	}

	public void setHttpProxy(Proxy proxy, String proxyString) {
		proxy.setHttpProxy(proxyString);
	}
	
	public void setSslProxy(Proxy proxy, String proxyString) {
		proxy.setSslProxy(proxyString);
	}

}
