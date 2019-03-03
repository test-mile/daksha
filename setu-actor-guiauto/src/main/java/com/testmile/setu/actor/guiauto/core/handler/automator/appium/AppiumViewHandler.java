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
import java.util.Set;

import com.testmile.setu.actor.SetuAgentConfig;
import com.testmile.setu.actor.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.automator.HybridViewHandler;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class AppiumViewHandler extends AbstractWDHandler implements HybridViewHandler{
	private AppiumDriver<MobileElement> appiumDriver;

	public AppiumViewHandler(AppiumDriver<MobileElement>  driver, SetuAgentConfig config) throws Exception {
		super(driver, config);
		appiumDriver = driver;
	}

	private AppiumDriver<MobileElement>  getAppiumDriver(){
		return this.appiumDriver;
	}
	
	public void switchToViewContext(String view) throws Exception {
		this.getAppiumDriver().context(view);
	}

	public String getCurrentViewContext() throws Exception {
		return this.getAppiumDriver().getContext();
	}
	
	public Set<String> getAllViewContexts() throws Exception {
		return this.getAppiumDriver().getContextHandles();
	}
	
}
