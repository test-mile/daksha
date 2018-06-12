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
package daksha.core.leaping.appium.lib.base;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import daksha.core.leaping.appium.interfaces.AppiumElementProxy;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.interfaces.UiElementIdentifier;
import daksha.core.leaping.lib.base.BaseUiElementProxy;
import daksha.tpi.leaping.enums.UiElementType;
import daksha.tpi.leaping.interfaces.AppiumGuiAutomator;
import daksha.tpi.leaping.interfaces.GuiElement;
import io.appium.java_client.MobileElement;

public class DefaultAppiumElementProxy extends BaseUiElementProxy implements AppiumElementProxy {

	private AppiumGuiAutomator uiDriver = null;
	public MobileElement toolElement = null;
	private Select selectElement = null;
	private List<MobileElement> toolElements = null;
	private List<By> toolFindByQueue = null;
	
	public DefaultAppiumElementProxy(GuiElement uiElement) {
		super(uiElement);
	}
	
	public DefaultAppiumElementProxy(AppiumGuiAutomator uiDriver, GuiElement uiElement) {
		this(uiElement);
		this.uiDriver = uiDriver;
		this.setAutomatorName(uiDriver.getName());
	}
	
	@Override
	public AppiumGuiAutomator getAppiumUiDriver() {
		return uiDriver;
	}

	@Override
	public MobileElement getToolElement() throws Exception {
		return toolElement;
	}

	@Override
	public MobileElement getToolElementWithRetry() throws Exception {
		MobileElement element = this.getToolElement();
		if (element == null){
			identify();
			return this.getToolElement();
		} else {
			return element;
		}
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
	public void setToolElement(MobileElement toolElement) {
		this.toolElement = toolElement;
	}

	@Override
	public void setToolElements(List<MobileElement> elements) {
		this.toolElements = elements;
	}

	@Override
	public List<MobileElement> getToolElements() {
		return toolElements;
	}

	@Override
	public void setRawToolElement(Object toolElementObject) {
		setToolElement((MobileElement) toolElementObject);
	}

	@Override
	public void setRawToolElements(Object toolElementsObject) {
		setToolElements((List<MobileElement>) toolElementsObject);
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
	public GuiElement getUiElementWrapperForToolElement(MobileElement toolElement) throws Exception {
		return getElementWrapper(this.getUiElement().getMetaData(), toolElement, this.getUiElement().getLoaderType());	
	}

	@Override
	public GuiElement getElementWrapper(UiElementIdentifier elementMetaData, MobileElement toolElement, ElementLoaderType elementLoaderType) throws Exception {
			GuiElement childUiElement = getAppiumUiDriver().declareElement(elementMetaData);
			// Set properties
			childUiElement.setName(this.getUiElement().getName() + " (instance)");
	//		childUiElement.setMetaData(this.getUiElement().getMetaData());
			childUiElement.setCompositePageName(this.getUiElement().getCompositePageName());
			childUiElement.setPageLabel(this.getUiElement().getPageLabel());
			
			setElementForChildUiElement(childUiElement, toolElement);
			return childUiElement;
		}

	@Override
	public void setElementForUiElement(MobileElement toolElement) throws Exception {
		this.getUiElement().setElement(toolElement);
		this.getUiElement().switchOffCompositeFlag();
		this.setToolElement(toolElement);
		decorateSingleUiElement(this.getUiElement(), toolElement);		
	}

	@Override
	public void setElementsForUiElement(List<MobileElement> toolElements) throws Exception {
		this.getUiElement().setElements(toolElements);
		this.getUiElement().switchOnCompositeFlag();
		this.setToolElements(toolElements);
	}

	@Override
	public void setElementForChildUiElement(GuiElement childUiElement, MobileElement toolElement) throws Exception {
		childUiElement.setElement(toolElement);
		childUiElement.switchOffCompositeFlag();
		childUiElement.getProxy().setRawToolElement(toolElement);
		decorateSingleUiElement(childUiElement, toolElement);	
	}

	@Override
	public void setElementsForChildUiElement(GuiElement childUiElement, List<MobileElement> toolElements) throws Exception {
		childUiElement.setElements(toolElements);
		childUiElement.switchOnCompositeFlag();
		childUiElement.getProxy().setRawToolElements(toolElements);
	}

	@Override
	public void decorateSingleUiElement(GuiElement uiElement, MobileElement toolElement) throws Exception {
		AppiumGuiAutomator automator = getAppiumUiDriver();
		switch (automator.getElementType(toolElement)){
		case DROPDOWN: 
			Select select = automator.convertToSelectElement(toolElement);
			uiElement.setElement(select);
			uiElement.setType(UiElementType.DROPDOWN);
			this.setSelectElement(select);
			this.setElementType(UiElementType.DROPDOWN);
			break;
		case RADIO:
			List<MobileElement> elements  = null;
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
		AppiumGuiAutomator automator = this.getAppiumUiDriver();
		MobileElement toolElement  = null;
		for (By by: getToolFindersQueue()){
			try{
				toolElement = automator.findElement(by);
				break;
			} catch (Exception e){
				//Do nothing
			}
		}
		if (toolElement == null){
			throw new Exception("Element Identification failed.");
		}
		setElementForUiElement(toolElement);
	}

	@Override
	public void identifyAll() throws Exception {
		AppiumGuiAutomator automator = this.getAppiumUiDriver();
		List<MobileElement> toolElements  = null;
		for (By by: getToolFindersQueue()){
			try{
				toolElements = automator.findElements(by);
				break;
			} catch (Exception e){
				//Do nothing
			}
		}
		if (toolElements == null){
			throw new Exception("Multiple Element identification failed.");
		}
		setElementsForUiElement(toolElements);
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
		MobileElement appiumElement = this.getToolElements().get(index);
		return this.getUiElementWrapperForToolElement(appiumElement);
	}

	@Override
	public GuiElement getInstanceByText(String text) throws Exception {
		identifyAllIfNull();
		for (MobileElement element: this.getToolElements()){
			if (element.getText().equals(text)){
				return this.getUiElementWrapperForToolElement(element);
			}
		}
		throw new Exception("None of the element instances has the specified text.");
	}

	@Override
	public GuiElement getInstanceByTextContent(String text) throws Exception {
		identifyAllIfNull();
		for (MobileElement element: this.getToolElements()){
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
		for (MobileElement appiumElement: this.getToolElements()){
			uiElements.add(this.getUiElementWrapperForToolElement(appiumElement));
		}
		return uiElements;
	}

	@Override
	public void setAppiumUiDriver(AppiumGuiAutomator automator) {
		this.uiDriver = automator;
	}
	

	@Override
	public void focus() throws Exception {
		getAppiumUiDriver().focus(this.getToolElementWithRetry());
	}
	

	@Override
	public void enterText(String text) throws Exception {
		getAppiumUiDriver().enterText(this.getToolElementWithRetry(), text);
	}
	

	@Override
	public void setText(String text) throws Exception {
		getAppiumUiDriver().setText(this.getToolElementWithRetry(), text);
	}
	

	@Override
	public void clearText() throws Exception {
		getAppiumUiDriver().clearText(this.getToolElementWithRetry());
	}
	

	@Override
	public boolean isPresent() throws Exception {
		boolean present = false;
		AppiumGuiAutomator automator = getAppiumUiDriver();
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
		AppiumGuiAutomator automator = getAppiumUiDriver();
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
		AppiumGuiAutomator automator = getAppiumUiDriver();
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
		AppiumGuiAutomator automator = getAppiumUiDriver();
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
	public void waitForPresence() throws Exception {
		AppiumGuiAutomator automator = getAppiumUiDriver();
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
		AppiumGuiAutomator automator = getAppiumUiDriver();
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
		AppiumGuiAutomator automator = getAppiumUiDriver();
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
	public void click() throws Exception {
		getAppiumUiDriver().click(getToolElementWithRetry());
	}


	@Override
	public void check() throws Exception {
		getAppiumUiDriver().check(getToolElementWithRetry());
	}


	@Override
	public void uncheck() throws Exception {
		getAppiumUiDriver().uncheck(getToolElementWithRetry());
	}


	@Override
	public void toggle() throws Exception {
		getAppiumUiDriver().toggle(getToolElementWithRetry());
	}

	// Property API

	@Override
	public String getText() throws Exception {
		return getAppiumUiDriver().getText(getToolElementWithRetry());
	}


	@Override
	public String getValue() throws Exception {
		return getAppiumUiDriver().getValue(getToolElementWithRetry());
	}
	

	@Override
	public String getAttribute(String attr) throws Exception {
		return getAppiumUiDriver().getAttribute(getToolElementWithRetry(), attr);
	}
	

	@Override
	public int getWaitTime() throws Exception {
		return getAppiumUiDriver().getWaitTime();
	}


	@Override
	public File takeScreenshot() throws Exception{
		return getAppiumUiDriver().takeScreenshot();
	}
	
	/*
	 * Selection API
	 */
	

	@Override
	public void selectDropDownLabel(String label) throws Exception{
		getAppiumUiDriver().selectDropDownLabel(this.getSelectElementWithRetry(), label);		
	}
	

	@Override
	public void selectRadioLabel(String label) throws Exception{
		MobileElement element = getAppiumUiDriver().chooseElementBasedOnParentText(this.getToolElements(), label);
		getAppiumUiDriver().clickIfNotSelected(element);		
	}
	

	@Override
	public void selectDropDownValue(String value) throws Exception{
		getAppiumUiDriver().selectDropDownValue(this.getSelectElementWithRetry(), value);		
	}
	

	@Override
	public void selectRadioValue(String value) throws Exception{
		MobileElement element = getAppiumUiDriver().chooseElementBasedOnValue(this.getToolElements(), value);
		getAppiumUiDriver().clickIfNotSelected(element);		
	}


	@Override
	public void selectDropDownOptionAtIndex(int index) throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		getAppiumUiDriver().selectDropDownOptionAtIndex(selectControl, index);		
	}

	@Override
	public void selectRadioOptionAtIndex(int index) throws Exception{
		getAppiumUiDriver().clickIfNotSelected(this.getToolElements().get(index));		
	}


	@Override
	public boolean isDropDownLabelSelected(String label) throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getAppiumUiDriver().isDropDownSelectedText(selectControl, label);		
	}

	@Override
	public boolean isRadioLabelSelected(String label) throws Exception{
		return getAppiumUiDriver().isSelectedElementParentText(this.getToolElements(), label);		
	}
	

	@Override
	public boolean isDropDownValueSelected(String value) throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getAppiumUiDriver().isDropDownSelectedValue(selectControl, value);	
	}

	@Override
	public boolean isRadioValueSelected(String value) throws Exception{
		return getAppiumUiDriver().isSelectedValue(this.getToolElements(), value);	
	}


	@Override
	public boolean isDropDownOptionSelectedAtIndex(int index) throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getAppiumUiDriver().isDropDownSelectedIndex(selectControl, index);		
	}

	@Override
	public boolean isRadioOptionSelectedAtIndex(int index) throws Exception{
		return getAppiumUiDriver().isSelectedIndex(this.getToolElements(), index);
	}
	

	@Override
	public List<String> getDropDownLabels() throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getAppiumUiDriver().getDropDownOptionLabels(selectControl);		
	}

	@Override
	public List<String> getRadioLabels() throws Exception{
		return getAppiumUiDriver().getRadioButtonLabels(this.getToolElements());
	}


	@Override
	public List<String> getDropDownValues() throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getAppiumUiDriver().getDropDownOptionValues(selectControl);		
	}

	@Override
	public List<String> getRadioValues() throws Exception{
		return getAppiumUiDriver().getRadioButtonValues(this.getToolElements());		
	}


	@Override
	public int getDropDownOptionCount() throws Exception{
		Select selectControl = this.getSelectElementWithRetry();
		return getAppiumUiDriver().getDropDownOptionCount(selectControl);		
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
}
