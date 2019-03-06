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

package com.testmile.setu.actor.guiauto.core.automator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.SeleniumAlertHandler;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.SeleniumBrowserHandler;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.SeleniumElementFinder;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.SeleniumFrameHandler;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.SeleniumJSExecutor;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.SeleniumScreenshoter;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.SeleniumScroller;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.SeleniumWindowHandler;
import com.testmile.trishanku.tpi.enums.Browser;

public class SeleniumGuiAutomator extends BaseGuiAutomator {
	private WebDriver driver = null;
	protected Capabilities capabilities = null;
	private Browser browser = null;
	
	public SeleniumGuiAutomator(WebDriver driver, SetuActorConfig config) throws Exception{
		super(config);
		System.out.println("hfdgkhdfgkjhk");
		this.driver = driver;
		initComponents();
	}
	
	private void initComponents() throws Exception{
		if(this.browser != Browser.SAFARI){
			getWebDriver().manage().timeouts().pageLoadTimeout(this.getConfig().getMaxWaitTime(), TimeUnit.SECONDS);
		}
		
		this.setAlertHandler(new SeleniumAlertHandler(this.driver, getConfig()));
		this.setBrowserHandler(new SeleniumBrowserHandler(this.driver, getConfig()));
		this.setFrameHandler(new SeleniumFrameHandler(this.driver, getConfig()));
		this.setJsExecutor(new SeleniumJSExecutor(this.driver, getConfig()));
		this.setScreenshoter(new SeleniumScreenshoter(this.driver, getConfig()));
		this.setScroller(new SeleniumScroller(this.driver, getConfig()));
		this.setWindowHandler(new SeleniumWindowHandler(driver, getConfig()));
		this.setElementFinder(new SeleniumElementFinder(driver, getConfig()));
	}

	private WebDriver getWebDriver() {
		return driver;
	}
	
	public void quit() throws Exception{
		this.getBrowserHandler().close();
	}

}
