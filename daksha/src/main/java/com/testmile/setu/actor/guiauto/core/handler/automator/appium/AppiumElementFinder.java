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

package com.testmile.setu.actor.guiauto.core.handler.automator.appium;

import java.util.List;

import org.openqa.selenium.By;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.core.element.AppiumGuiElement;
import com.testmile.setu.actor.guiauto.core.handler.automator.selenium.AbstractSeleniumFinder;
import com.testmile.setu.actor.guiauto.tpi.element.GuiElement;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumElementFinder extends AbstractSeleniumFinder<MobileElement> {
	private AppiumDriver<MobileElement> appiumDriver;
	
	public AppiumElementFinder(AppiumDriver<MobileElement> driver, SetuActorConfig config) throws Exception {
		super(driver, config);
		this.appiumDriver = driver;
	}

	protected GuiElement convertToGuiElement(MobileElement element) throws Exception {
		return new AppiumGuiElement(appiumDriver, element, this.getConfig());
	}
	
	protected List<MobileElement> findAllInContainer(By by) throws Exception{
		return appiumDriver.findElements(by);
	}
	
	protected By getLocator(String by, String value) throws Exception{
		return (new AppiumLocator(by, value)).getByObject();
	}
	
}
