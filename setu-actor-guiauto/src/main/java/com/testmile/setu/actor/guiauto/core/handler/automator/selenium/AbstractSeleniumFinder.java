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

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.testmile.setu.actor.SetuActorConfig;
import com.testmile.setu.actor.guiauto.core.element.DefaultGuiMultiElement;
import com.testmile.setu.actor.guiauto.core.element.SeleniumGuiElement;
import com.testmile.setu.actor.guiauto.core.handler.automator.AbstractWDHandler;
import com.testmile.setu.actor.guiauto.tpi.automator.ElementFinder;
import com.testmile.setu.actor.guiauto.tpi.element.GuiElement;
import com.testmile.setu.actor.guiauto.tpi.element.GuiMultiElement;

public abstract class AbstractSeleniumFinder<E extends WebElement> extends AbstractWDHandler implements ElementFinder {

	public AbstractSeleniumFinder(WebDriver driver, SetuActorConfig config) throws Exception {
		super(driver, config);
	}

	protected GuiMultiElement convetToMultiGuiElement(List<E> rawElements) throws Exception {
		List<GuiElement> elements = new ArrayList<GuiElement>();
		for (E element: rawElements) {
			elements.add(convertToGuiElement(element));
		}
		return new DefaultGuiMultiElement(elements);
	}
	
	protected abstract GuiElement convertToGuiElement(E element) throws Exception;
	protected abstract List<E> findAllInContainer(By by) throws Exception;
	protected abstract By getLocator(String by, String value) throws Exception;
	
	@Override
	public GuiMultiElement findAll(String by, String value) throws Exception {
		return convetToMultiGuiElement(this.findAllInContainer(this.getLocator(by, value)));
	}

	@Override
	public GuiElement find(String by, String value) throws Exception {
		return this.convertToGuiElement(this.findAllInContainer(this.getLocator(by, value)).get(0));
	}
	
}