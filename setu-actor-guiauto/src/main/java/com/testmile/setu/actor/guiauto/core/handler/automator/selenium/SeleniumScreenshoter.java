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

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.testmile.setu.actor.SetuAgentConfig;
import com.testmile.setu.actor.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.Screenshoter;

public class SeleniumScreenshoter extends AbstractWDHandler implements Screenshoter{
	private TakesScreenshot screenshotDriver;
	
	public SeleniumScreenshoter(WebDriver driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		this.screenshotDriver = (TakesScreenshot) driver;
	}

	@Override
	public String takeScreenshot() throws Exception {
        return screenshotDriver.getScreenshotAs(OutputType.BASE64);
	}
}