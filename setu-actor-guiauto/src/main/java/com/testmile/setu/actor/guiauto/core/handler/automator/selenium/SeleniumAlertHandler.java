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

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.AlertHandler;

public class SeleniumAlertHandler extends AbstractWDHandler implements AlertHandler {
	private WebDriverWait waiter;
	
	public SeleniumAlertHandler(WebDriver driver, SetuActorConfig config) throws Exception {
		super(driver, config);
		this.waiter = new WebDriverWait(driver, 1);
	}
	
	protected void validateAlertSupport() throws Exception{
		return;
	}
	
	@Override
	public boolean isAlertPresent() throws Exception {
		try {
			this.waiter.until(ExpectedConditions.alertIsPresent());
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public void confirmAlert() throws Exception {
		validateAlertSupport();
		this.getWebDriver().switchTo().alert().accept();
	}

	@Override
	public void dismissAlert() throws Exception {
		validateAlertSupport();
		this.getWebDriver().switchTo().alert().dismiss();
	}

	@Override
	public void sendTextToAlert(String text) throws Exception {
		validateAlertSupport();
		System.out.println(text);
		this.getWebDriver().switchTo().alert().sendKeys(text);
	}

	@Override
	public String getTextFromAlert() throws Exception {
		validateAlertSupport();
		return this.getWebDriver().switchTo().alert().getText();
	}
}
