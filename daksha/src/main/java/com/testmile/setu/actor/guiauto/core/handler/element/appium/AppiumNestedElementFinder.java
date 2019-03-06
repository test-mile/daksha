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

package com.testmile.setu.actor.guiauto.core.handler.element.appium;

import java.util.List;

import org.openqa.selenium.By;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.core.handler.automator.appium.AppiumElementFinder;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumNestedElementFinder extends AppiumElementFinder{
	private MobileElement mobileElement;

	public AppiumNestedElementFinder(AppiumDriver<MobileElement> driver, MobileElement element, SetuActorConfig config) throws Exception {
		super(driver, config);
		this.mobileElement = element;
	}
	
	protected List<MobileElement> findAllInContainer(By by) throws Exception{
		return this.mobileElement.findElements(by);
	}
}
