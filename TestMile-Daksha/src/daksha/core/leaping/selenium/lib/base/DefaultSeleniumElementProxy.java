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
package daksha.core.leaping.selenium.lib.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.interfaces.UiElementIdentifier;
import daksha.core.leaping.lib.base.BaseUiElementProxy;
import daksha.core.leaping.selenium.interfaces.SeleniumElementProxy;
import daksha.tpi.leaping.enums.UiElementType;
import daksha.tpi.leaping.interfaces.SeleniumGuiAutomator;
import daksha.tpi.leaping.interfaces.GuiElement;

public class DefaultSeleniumElementProxy extends BaseUiElementProxy implements SeleniumElementProxy{
	
	private SeleniumGuiAutomator uiDriver = null;
	private WebElement toolElement = null;
	private Select selectElement = null;
	private List<WebElement> toolElements = null;
	private List<By> toolFindByQueue = null;
	
	public DefaultSeleniumElementProxy(SeleniumGuiAutomator uiDriver, GuiElement uiElement){
		super(uiElement);
		this.setSeleniumUiDriver(uiDriver);
	}

	@Override
	public SeleniumGuiAutomator getSeleniumUiDriver() {
		return uiDriver;
	}

	@Override
	public void setSeleniumUiDriver(SeleniumGuiAutomator uiDriver) {
		this.uiDriver = uiDriver;
	}

	@Override
	public WebElement getToolElement() throws Exception {
		return toolElement;
	}

	@Override
	public WebElement getToolElementWithRetry() throws Exception {
		WebElement element = this.getToolElement();
		if (element == null){
			identify();
			return this.getToolElement();
		} else {
			return element;
		}
	}

	@Override
	public List<WebElement> getToolElements() {
		return toolElements;
	}

	@Override
	public Select getSelectElement() {
		return selectElement;
	}

	@Override
	public Select getSelectElementWithRetry() throws Exception {
		Select element = this.getSelectElement();
		if (element == null){
			identify();
			return this.getSelectElement();
		} else {
			return element;
		}
	}

	@Override
	public void setToolElement(WebElement toolElement) {
		this.toolElement = toolElement;
	}

	@Override
	public void setToolElements(List<WebElement> elements) {
		this.toolElements = elements;
	}

	@Override
	public void setRawToolElement(Object toolElementObject) throws Exception {
		setToolElement((WebElement) toolElementObject);
	}

	@Override
	public void setRawToolElements(Object toolElementsObject) {
		setToolElements((List<WebElement>) toolElementsObject);
	}

	@Override
	public void setSelectElement(Select selectElement) {
		this.selectElement = selectElement;
	}

	@Override
	public List<By> getToolFindersQueue() {
		return toolFindByQueue;		
	}

	@Override
	public List<By> getToolFindersQueueObject() {
		return getToolFindersQueue();		
	}

	@Override
	public void setFindersQueue(List<By> findByQueue) {
		this.toolFindByQueue = findByQueue;
	}

	@Override
	public boolean isCompositeElementIdentified() throws Exception {
		if (!this.isComposite()){
			return false;
		} else {
			return ((this.getToolElements() != null) && (this.getToolElements().size() > 0));
		}
	}

	@Override
	public boolean isSingularElementIdentified() throws Exception {
		if (this.getElementType() == UiElementType.DROPDOWN){
			return this.getSelectElement() != null;
		} else {
			return this.getToolElement() != null;
		}
	}

	@Override
	public int getElementCountForCompositeElement() throws Exception {
		if (isCompositeElementIdentified()){
			return this.getToolElements().size();
		} else {
			return 0;
		}
	}

	@Override
	public GuiElement getUiElementWrapperForToolElement(WebElement toolElement) throws Exception {
		return getElementWrapper(this.getUiElement().getMetaData(), toolElement, this.getUiElement().getLoaderType());
	}

	@Override
	public GuiElement getElementWrapper(UiElementIdentifier elementMetaData, WebElement toolElement, ElementLoaderType loaderType) throws Exception {
		GuiElement childUiElement = this.getSeleniumUiDriver().declareElement(elementMetaData);
	
		// Set properties
		childUiElement.setName(this.getUiElement().getName() + " (instance)");
		//childUiElement.setMetaData(this.getUiElement().getMetaData()); // is set as a part of element construction
		childUiElement.setCompositePageName(this.getUiElement().getCompositePageName());
		childUiElement.setPageLabel(this.getUiElement().getPageLabel());
		setElementForChildUiElement(childUiElement, toolElement);
		return childUiElement;
	}

	@Override
	public void setElementForUiElement(WebElement toolElement) throws Exception {
		this.getUiElement().setElement(toolElement);
		this.getUiElement().switchOffCompositeFlag();
		this.setComposite(false);
		this.setToolElement(toolElement);
		decorateSingleUiElement(this.getUiElement(), toolElement);	
	}

	@Override
	public void setElementsForUiElement(List<WebElement> toolElements) throws Exception {
		this.getUiElement().setElements(toolElements);
		this.getUiElement().switchOnCompositeFlag();
		this.setComposite(true);
		this.setToolElements(toolElements);
	}

	@Override
	public void setElementForChildUiElement(GuiElement childUiElement, WebElement toolElement) throws Exception {
		childUiElement.setElement(toolElement);
		childUiElement.switchOffCompositeFlag();
		childUiElement.getProxy().setRawToolElement(toolElement);
		decorateSingleUiElement(childUiElement, toolElement);	
	}

	@Override
	public void setElementsForChildUiElement(GuiElement childUiElement, List<WebElement> toolElements) throws Exception {
		childUiElement.setElements(toolElements);
		childUiElement.switchOnCompositeFlag();
		childUiElement.getProxy().setRawToolElements(toolElements);
	}

	@Override
	public void decorateSingleUiElement(GuiElement uiElement, WebElement toolElement) throws Exception {
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		switch (automator.getElementType(toolElement)){
		case DROPDOWN: 
			Select select = automator.convertToSelectElement(toolElement);
			uiElement.setElement(select);
			uiElement.setType(UiElementType.DROPDOWN);
			this.setSelectElement(select);
			this.setElementType(UiElementType.DROPDOWN);
			break;
		case RADIO:
			List<WebElement> elements  = null;
			for (By by: getToolFindersQueue()){
				try{
					elements = automator.findElements(by);
					break;
				} catch (Exception e){
					//Do nothing
				}
			}
			if (elements == null){
				throw new Exception("Not able to find radio elements.");
			}
			uiElement.setElements(elements);
			uiElement.setType(UiElementType.RADIO);
			this.setToolElements(elements);
			this.setElementType(UiElementType.RADIO);
			break;
		default: uiElement.setType(UiElementType.GENERIC); this.setElementType(UiElementType.GENERIC);
		}		
	}

	@Override
	public void identify() throws Exception {
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		WebElement wdElement  = null;
		for (By by: getToolFindersQueue()){
			try{
				wdElement = automator.findElement(by);
				break;
			} catch (Exception e){
				//Do nothing
			}
		}
		if (wdElement == null){
			throw new Exception("Element Identification failed.");
		}
		setElementForUiElement(wdElement);
	}

	@Override
	public void identifyAll() throws Exception {
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		List<WebElement> wdElements  = null;
		for (By by: getToolFindersQueue()){
			try{
				wdElements = automator.findElements(by);
				break;
			} catch (Exception e){
				//Do nothing
			}
		}
		if (wdElements == null){
			throw new Exception("Multiple Element identification failed.");
		}
		setElementsForUiElement(wdElements);
	}

	@Override
	public void identifyAtIndex(int index) throws Exception {
		this.prepareIndexIndetification(index);
		this.setElementForUiElement(this.getToolElements().get(index));
	}

	@Override
	public void assignElementAtIndexFromMatches(int index) throws Exception {
		this.setElementForUiElement(this.getToolElements().get(index));
	}

	@Override
	public GuiElement getInstanceAtIndex(int index) throws Exception {
		identifyAllIfNull();
		WebElement toolElement = this.getToolElements().get(index);
		return this.getUiElementWrapperForToolElement(toolElement);
	}

	@Override
	public GuiElement getInstanceByText(String text) throws Exception {
		identifyAllIfNull();
		for (WebElement element: this.getToolElements()){
			if (element.getText().equals(text)){
				return this.getUiElementWrapperForToolElement(element);
			}
		}
		throw new Exception("None of the element instances has the specified text.");
	}

	@Override
	public GuiElement getInstanceByTextContent(String text) throws Exception {
		identifyAllIfNull();
		for (WebElement element: this.getToolElements()){
			if (element.getText().contains(text)){
				return this.getUiElementWrapperForToolElement(element);
			}
		}
		throw new Exception("None of the element instances has the specified text content.");
	}

	@Override
	public List<GuiElement> getAllInstances() throws Exception {
		identifyAllIfNull();
		List<GuiElement> uiElements = new ArrayList<GuiElement>();
		for (WebElement toolElement: this.getToolElements()){
			uiElements.add(this.getUiElementWrapperForToolElement(toolElement));
		}
		return uiElements;
	}
	

	@Override
	public void focus() throws Exception {
		getSeleniumUiDriver().focus(this.getToolElementWithRetry());
	}


	@Override
	public void enterText(String text) throws Exception {
		getSeleniumUiDriver().enterText(this.getToolElementWithRetry(), text);
	}


	@Override
	public void setText(String text) throws Exception {
		getSeleniumUiDriver().setText(this.getToolElementWithRetry(), text);
	}


	@Override
	public void clearText() throws Exception {
		getSeleniumUiDriver().clearText(this.getToolElementWithRetry());
	}


	@Override
	public void switchToFrame() throws Exception{
		getSeleniumUiDriver().switchToFrame(getToolElementWithRetry());
	}
	

	@Override
	public boolean isPresent() throws Exception {
		boolean present = false;
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for(By by: this.getToolFindersQueue()){
			try{
				present = automator.isElementPresent(by);
				if (present) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return present;
	}
	
	@Override
	public boolean isAbsent() throws Exception {
		boolean present = false;
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for(By by: this.getToolFindersQueue()){
			try{
				present = automator.isElementAbsent(by);
				if (present) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return present;
	}
	
	@Override
	public boolean isVisible() throws Exception {
		boolean present = false;
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for(By by: this.getToolFindersQueue()){
			try{
				present = automator.isElementVisible(by);
				if (present) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return present;
	}

	@Override
	public boolean isInvisible() throws Exception {
		boolean present = false;
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for(By by: this.getToolFindersQueue()){
			try{
				present = automator.isElementInvisible(by);
				if (present) break;
			} catch (Exception e){
				// Do nothing
			}
		}

		return present;
	}
	

	@Override
	public void click() throws Exception {
		getSeleniumUiDriver().click(getToolElementWithRetry());
	}


	@Override
	public void waitForPresence() throws Exception {
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for(By by: this.getToolFindersQueue()){
			try{
				automator.waitForElementPresence(by);
				return;
			} catch (Exception e){
				// Do nothing
			}
		}
		throw new Exception("Not able to establish element presence after polling for it.");
	}
	
	@Override
	public void waitForAbsence() throws Exception {
		try{
			waitForPresence();
			throw new Exception("Not able to establish element absence after polling for it.");
		} catch (Exception e){
			// Element is absent as expected. Do nothing.
		}		
	}
	
	@Override
	public void waitForVisibility() throws Exception {
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for(By by: this.getToolFindersQueue()){
			try{
				automator.waitForElementVisibility(by);
				return;
			} catch (Exception e){
				// Do nothing
			}
		}
		throw new Exception("Not able to establish element presence after polling for it.");
	}
	
	@Override
	public void waitForInvisibility() throws Exception {
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for(By by: this.getToolFindersQueue()){
			try{
				automator.waitForElementInvisibility(by);
				return;
			} catch (Exception e){
				// Do nothing
			}
		}
		throw new Exception("Not able to establish element presence after polling for it.");
	}


	@Override
	public void check() throws Exception {
		getSeleniumUiDriver().check(getToolElementWithRetry());
	}


	@Override
	public void uncheck() throws Exception {
		getSeleniumUiDriver().uncheck(getToolElementWithRetry());
	}


	@Override
	public void toggle() throws Exception {
		getSeleniumUiDriver().toggle(getToolElementWithRetry());
	}

	// Property API

	@Override
	public String getText() throws Exception {
		return getSeleniumUiDriver().getText(getToolElementWithRetry());
	}


	@Override
	public String getValue() throws Exception {
		return getSeleniumUiDriver().getValue(getToolElementWithRetry());
	}


	@Override
	public String getAttribute(String attr) throws Exception {
		return getSeleniumUiDriver().getAttribute(getToolElementWithRetry(), attr);
	}
	

	@Override
	public void hover() throws Exception {
		getSeleniumUiDriver().hover(getToolElementWithRetry());
	}


	@Override
	public void hoverAndClick() throws Exception {
		getSeleniumUiDriver().hoverAndClick(getToolElementWithRetry());
	}


	@Override
	public void rightClick() throws Exception {
		getSeleniumUiDriver().rightClick(getToolElementWithRetry());
	}
	

	@Override
	public int getWaitTime() throws Exception {
		return getSeleniumUiDriver().getWaitTime();
	}


	@Override
	public File takeScreenshot() throws Exception{
		return getSeleniumUiDriver().takeScreenshot();
	}

	/*
	 * Selection API
	 */


	@Override
	public void selectDropDownLabel(String label) throws Exception{
		getSeleniumUiDriver().selectDropDownLabel(this.getSelectElementWithRetry(), label);		
	}


	@Override
	public void selectRadioLabel(String label) throws Exception{
		WebElement element = getSeleniumUiDriver().chooseElementBasedOnParentText(this.getToolElements(), label);
		getSeleniumUiDriver().clickIfNotSelected(element);		
	}


	@Override
	public void selectDropDownValue(String value) throws Exception{
		getSeleniumUiDriver().selectDropDownValue(this.getSelectElementWithRetry(), value);		
	}


	@Override
	public void selectRadioValue(String value) throws Exception{
		WebElement element = getSeleniumUiDriver().chooseElementBasedOnValue(this.getToolElements(), value);
		getSeleniumUiDriver().clickIfNotSelected(element);		
	}


	@Override
	public void selectDropDownOptionAtIndex(int index) throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		getSeleniumUiDriver().selectDropDownOptionAtIndex(selectControl, index);		
	}

	@Override
	public void selectRadioOptionAtIndex(int index) throws Exception{
		getSeleniumUiDriver().clickIfNotSelected(this.getToolElements().get(index));		
	}


	@Override
	public boolean isDropDownLabelSelected(String label) throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getSeleniumUiDriver().isDropDownSelectedText(selectControl, label);		
	}

	@Override
	public boolean isRadioLabelSelected(String label) throws Exception{
		return getSeleniumUiDriver().isSelectedElementParentText(this.getToolElements(), label);		
	}


	@Override
	public boolean isDropDownValueSelected(String value) throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getSeleniumUiDriver().isDropDownSelectedValue(selectControl, value);	
	}

	@Override
	public boolean isRadioValueSelected(String value) throws Exception{
		return getSeleniumUiDriver().isSelectedValue(this.getToolElements(), value);	
	}


	@Override
	public boolean isDropDownOptionSelectedAtIndex(int index) throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getSeleniumUiDriver().isDropDownSelectedIndex(selectControl, index);		
	}

	@Override
	public boolean isRadioOptionSelectedAtIndex(int index) throws Exception{
		return getSeleniumUiDriver().isSelectedIndex(this.getToolElements(), index);
	}


	@Override
	public List<String> getDropDownLabels() throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getSeleniumUiDriver().getDropDownOptionLabels(selectControl);		
	}

	@Override
	public List<String> getRadioLabels() throws Exception{
		return getSeleniumUiDriver().getRadioButtonLabels(this.getToolElements());
	}


	@Override
	public List<String> getDropDownValues() throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getSeleniumUiDriver().getDropDownOptionValues(selectControl);		
	}

	@Override
	public List<String> getRadioValues() throws Exception{
		return getSeleniumUiDriver().getRadioButtonValues(this.getToolElements());		
	}


	@Override
	public int getDropDownOptionCount() throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getSeleniumUiDriver().getDropDownOptionCount(selectControl);		
	}


	@Override
	public int getRadioOptionCount() throws Exception{
		return getElementCountForCompositeElement();	
	}

	

	//========================================================================================
	/*
	 * APIs that are called by an Element either for lazy identification or repeated identification.
	 *
	 */

	

	/*
	 * Composite Controls
	 */

	

	/*
	 * Chain actions
	 */


	@Override
	public void hoverAndClickElement(GuiElement uiElement) throws Exception{
		boolean success = false;
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for (By by1: this.getToolFindersQueue()){
			for(By by2: (List<By>) uiElement.getProxy().getToolFindersQueueObject()){
				try{
					automator.hoverAndClick(by1, by2);
					success = true;
					break;
				} catch (Exception e){
					// do nothing
				}
			}
			
			// The inner break comes here. Break outer loop if success.
			if(success) break;
		}

		if (!success){
			throw new Exception("Hover and Click Element failed.");
		}

	}


	@Override
	public void rightClickAndClickElement(GuiElement uiElement) throws Exception {
		boolean success = false;
		SeleniumGuiAutomator automator = getSeleniumUiDriver();
		for (By by1: this.getToolFindersQueue()){
			for(By by2: (List<By>) uiElement.getProxy().getToolFindersQueueObject()){
				try{
					automator.rightClickAndClick(by1, by2);
					success = true;
					break;
				} catch (Exception e){
					// do nothing
				}
			}
			
			// The inner break comes here. Break outer loop if success.
			if(success) break;
		}

		if (!success){
			throw new Exception("Right click and Click Element failed.");
		}
	}

}
