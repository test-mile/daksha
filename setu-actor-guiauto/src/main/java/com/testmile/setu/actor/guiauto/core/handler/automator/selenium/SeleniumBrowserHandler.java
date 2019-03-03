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

package com.testmile.setu.actor.guiauto.core.handler.automator.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.actor.SetuAgentConfig;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.BrowserHandler;

public class SeleniumBrowserHandler extends SeleniumJSExecutor implements BrowserHandler {
	private WebDriverWait wait;

	public SeleniumBrowserHandler(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		wait = new WebDriverWait(driver, this.getConfig().getMaxWaitTime());
	}
	
	protected void validateBrowserSupport() throws Exception{
		return;
	}
	
	@Override
	public void goTo(String url) throws Exception {
		validateBrowserSupport();
		getWebDriver().get(url);
		//validatePageLoad();
	}
	
	@Override
	public void refresh() throws Exception {
		validateBrowserSupport();
		getWebDriver().navigate().refresh();
		//validatePageLoad();
	}
	
	@Override
	public void back() throws Exception {
		validateBrowserSupport();
		getWebDriver().navigate().back();
		//validatePageLoad();
	}
	
	@Override
	public void forward() throws Exception {
		validateBrowserSupport();
		getWebDriver().navigate().forward();
		//validatePageLoad();
	}

	@Override
	public void close() throws Exception{
		validateBrowserSupport();
		getWebDriver().quit();
	}
	
	protected boolean isWebView() {
		return true;
	}

//	@Override
//	public void validatePageLoad() throws Exception {
//		validateBrowserSupport();
//		
//		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
//			public Boolean apply(WebDriver driver) {
//				return ((String) ((JavascriptExecutor) driver).executeScript("return document.readyState"))
//						.equals("complete");
//			}
//		};
//		wait.until(pageLoadCondition);
//		wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
//	}
	
	public String getPageTitle() {
		return getWebDriver().getTitle();
	}

}
