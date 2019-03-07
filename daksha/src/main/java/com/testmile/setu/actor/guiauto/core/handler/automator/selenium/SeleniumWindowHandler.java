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

import java.awt.Toolkit;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.WindowHandler;

public class SeleniumWindowHandler extends AbstractWDHandler implements WindowHandler {
	
	public SeleniumWindowHandler(WebDriver driver, SetuActorConfig config) throws Exception {
		super(driver, config);
	}

	@Override
	public void setWindowSize(int width, int height) throws Exception{
		this.getWebDriver().manage().window().setSize(new Dimension(width, height));
	}

	@Override
	public void maximizeWindow(){
		// Check for some property here. To override this default.
		try{
			getWebDriver().manage().window().maximize();
		} catch (WebDriverException e){
			java.awt.Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
			// This dimension is webdriver Dimension
			getWebDriver().manage().window().setSize(new Dimension((int)d.getWidth(), (int) d.getHeight()));
		}
	}

	@Override
	public String getCurrentWindowHandle() {
		return getWebDriver().getWindowHandle();
	}

	@Override
	public void focusOnWindow(String windowHandle){
		getWebDriver().switchTo().window(windowHandle); 		
	}
	
	@Override
	public void closeCurrentWindow(){
		getWebDriver().close();
	}

	@Override
	public String getTitle() {
		return getWebDriver().getTitle();
	}

	@Override
	public Map<String, Integer> getCurrentWindowSize() throws Exception {
		Dimension dim = this.getWebDriver().manage().window().getSize();
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("width",dim.getWidth());
		map.put("height", dim.getHeight());
		return map;
	}

	@Override
	public Set<String> getAllWindowHandles() throws Exception {
		return getWebDriver().getWindowHandles();
	}
}
