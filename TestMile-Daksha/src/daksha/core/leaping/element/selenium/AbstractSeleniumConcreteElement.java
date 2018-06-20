/*******************************************************************************
 * Copyright 2015-18 Test Mile Software Testing Pvt Ltd
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
package daksha.core.leaping.element.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import daksha.core.leaping.automator.ConcreteGuiAutomator;
import daksha.core.leaping.element.BaseConcreteSingleGuiElement;
import daksha.core.leaping.element.proxy.GuiElementProxy;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.core.leaping.identifier.GuiLocator;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.pageobject.Page;

public class AbstractSeleniumConcreteElement<D,E> extends BaseConcreteSingleGuiElement<D,E>{
	private D driver = null;
	private WebDriverWait waiter = null;
	
	public AbstractSeleniumConcreteElement(ConcreteGuiAutomator<D,E> automator, GuiElementProxy eProxy) throws Exception{
		super(automator, eProxy);
		setUpObjectVars();
	}
	
	public AbstractSeleniumConcreteElement(Page page, ConcreteGuiAutomator<D,E> automator, GuiElementProxy eProxy) throws Exception{
		super(page, automator, eProxy);
		setUpObjectVars();
	}	
	
	private void setUpObjectVars() throws Exception {
		this.driver = this.getAutomator().getUiDriverEngine();
		this.setWaiter(new WebDriverWait((WebDriver) driver, this.getAutomator().getWaitTime()));
	}
	
	protected WebDriverWait getWaiter() {
		return this.waiter;
	}

	protected void setWaiter(WebDriverWait waiter) {
		this.waiter = waiter;
	}
	
	private void waitUntilPresent(By findBy) throws Exception {
		getWaiter().until(ExpectedConditions.presenceOfElementLocated(findBy));
	}
	
	private void waitUntilVisible(By findBy) throws Exception {
		getWaiter().until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	private void waitUntilClickable(By findBy) throws Exception {
		getWaiter().until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	private WebElement asWebElement() throws Exception {
		return ((WebElement) this.getToolElementWithRetry());
	}

	@Override
	public void focus() throws Exception {
		this.asWebElement().sendKeys("");
	}


	@Override
	public void enterText(String text) throws Exception {
		this.waitUntilClickable();
		WebElement e = this.asWebElement();
		e.click();
		e.sendKeys(text);
	}


	@Override
	public void setText(String text) throws Exception {
		this.waitUntilClickable();
		WebElement e = this.asWebElement();
		e.click();
		e.clear();
		e.sendKeys(text);
	}


	@Override
	public void clearText() throws Exception {
		this.waitUntilClickable();
		WebElement e = this.asWebElement();
		e.click();
		e.clear();
	}

	@Override
	public void switchToFrame() throws Exception{
		((WebDriver) driver).switchTo().frame(this.asWebElement());
	}
	
	private boolean isPresent(By findBy) {
		try {
			waitUntilPresent(findBy);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
	
	@Override
	public boolean isPresent() throws Exception {
		boolean present = false;
		for(GuiLocator locator: this.getMetaData().getLocators()){
			try{
				By by = this.getIdentifier().getFinderType(locator.NAME, locator.VALUE);
				present = isPresent(by);
				if (present) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return present;
	}
	
	@Override
	public void waitUntilPresent() throws Exception {
		if (!isPresent()) {
			throw new Exception("Not present despite waiting.");
		}
	}
	
	@Override
	public boolean isAbsent() throws Exception {
		return !isPresent();
	}
	
	@Override
	public void waitUntilAbsent() throws Exception {
		if (!isAbsent()) {
			throw new Exception("Not absent despite waiting.");
		}
	}
	
	private boolean isVisible(By findBy) {
		try {
			waitUntilVisible(findBy);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
		
	@Override
	public boolean isVisible() throws Exception {
		boolean visible = false;
		for(GuiLocator locator: this.getMetaData().getLocators()){
			try{
				By by = this.getIdentifier().getFinderType(locator.NAME, locator.VALUE);
				visible = isVisible(by);
				if (visible) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return visible;
	}

	@Override
	public void waitUntilVisible() throws Exception {
		if (!isVisible()) {
			throw new Exception("Not visible despite waiting.");
		}
	}

	
	@Override
	public boolean isInvisible() throws Exception {
		return !isVisible();
	}
	
	@Override
	public void waitUntilInvisible() throws Exception {
		if (!isInvisible()) {
			throw new Exception("Not invisible despite waiting.");
		}
	}
	
	private boolean isClickable(By findBy) {
		try {
			waitUntilClickable(findBy);
			return true;
		} catch ( Exception e){
			return false;
		}
		
	}
	
	@Override
	public boolean isClickable() throws Exception {
		boolean clickable = false;
		for(GuiLocator locator: this.getMetaData().getLocators()){
			try{
				By by = this.getIdentifier().getFinderType(locator.NAME, locator.VALUE);
				clickable = isClickable(by);
				if (clickable) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return clickable;
	}
	
	@Override
	public void waitUntilClickable() throws Exception {
		if (!isClickable()) {
			throw new Exception("Not clickable despite waiting.");
		}
	}

	@Override
	public void click() throws Exception {
		if (this.isClickable()) {
			this.asWebElement().click();
		}
	}
	
	public boolean isSelected() throws Exception{
		return this.asWebElement().isSelected();
	}
	
	public boolean isChecked() throws Exception{
		return isSelected();
	}
	
	public void check() throws Exception {
		if (!isChecked()){
			this.asWebElement().click();
		}
	}

	public void uncheck() throws Exception {
		if (isChecked()){
			this.asWebElement().click();
		}
	}

	public void toggle() throws Exception {
		this.asWebElement().click();
	}

	@Override
	public String getText() throws Exception {
		return this.asWebElement().getText();
	}

	@Override
	public String getValue() throws Exception {
		return this.asWebElement().getAttribute("value");
	}


	@Override
	public String getAttribute(String attr) throws Exception {
		return this.asWebElement().getAttribute(attr);
	}
	
	private Actions getActionChain(){
		return new Actions((WebDriver) driver);
	}

	@Override
	public void hover() throws Exception {
		getActionChain().moveToElement(this.asWebElement()).perform();
	}
	
	public void moveToElementAndClick() throws Exception{
		Actions builder = getActionChain();
		WebElement element = this.asWebElement();
		builder.moveToElement(element).click(element).perform();
	}
	
	public void hoverAndClick() throws Exception {
		try {
			Actions builder = getActionChain();
			WebElement element = this.asWebElement();
			builder.moveToElement(element).perform();
			this.waitUntilClickable();
			element.click();			
		} catch (Exception e){
			moveToElementAndClick();
		}
	}

	public void hoverAndClickElement(String name) throws Exception{
		Actions builder =  null;
		GuiElementMetaData metaData = this.getPage().getPageDef().getMetaData(name);
		try {
			builder = getActionChain();
			builder.moveToElement(this.asWebElement()).perform();
			WebElement e2 = (WebElement) this.getIdentifier().find(metaData);
			e2.click();
		} catch (Exception e){
			try {
				builder = getActionChain();
				builder.moveToElement(this.asWebElement()).click((WebElement) this.getIdentifier().find(metaData)).perform();
			} catch (Exception f) {
				throw new Exception("Hover and Click Element failed.");
			}
		}
	}
	
	public void rightClick() throws Exception{
		this.getActionChain().contextClick(this.asWebElement()).perform();
	}
	
	public void rightClickAndClickElement(String name) throws Exception{
		GuiElementMetaData metaData = this.getPage().getPageDef().getMetaData(name);
		this.rightClick();
		WebElement e2 = (WebElement) this.getIdentifier().find(metaData);
		e2.click();	
	}

}
