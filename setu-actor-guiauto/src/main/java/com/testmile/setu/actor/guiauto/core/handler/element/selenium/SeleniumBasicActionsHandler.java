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

package com.testmile.setu.actor.guiauto.core.handler.element.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.core.handler.element.AbstractWDElementHandler;
import com.testmile.setu.actor.guiauto.tpi.handler.element.BasicActionsHandler;

public class SeleniumBasicActionsHandler extends AbstractWDElementHandler implements BasicActionsHandler {

	public SeleniumBasicActionsHandler(WebDriver driver, WebElement element, SetuActorConfig config) throws Exception {
		super(driver, element, config);
	}

	@Override
	public void sendText(String text) throws Exception {
		this.getWebElement().sendKeys(text);
	}

	@Override
	public void clearText() throws Exception {
		this.getWebElement().clear();
	}

	@Override
	public void submit() throws Exception {
		this.getWebElement().submit();
	}

	@Override
	public void click() throws Exception {
		this.getWebElement().click();
	}

}
