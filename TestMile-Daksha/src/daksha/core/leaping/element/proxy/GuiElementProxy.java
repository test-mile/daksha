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
package daksha.core.leaping.element.proxy;

import java.io.File;
import java.io.IOException;
import java.util.List;

import daksha.Daksha;
import daksha.core.batteries.config.Batteries;
import daksha.core.batteries.exceptions.Problem;
import daksha.core.leaping.element.ConcreteGuiElement;
import daksha.core.leaping.enums.ElementLoaderType;
import daksha.core.leaping.identifier.GuiElementMetaData;
import daksha.tpi.leaping.automator.GuiAutomator;
import daksha.tpi.leaping.element.GuiElement;
import daksha.tpi.leaping.element.MultiGuiElement;
import daksha.tpi.leaping.enums.UiAutomationContext;
import daksha.tpi.leaping.pageobject.Page;
import daksha.tpi.leaping.enums.GuiElementType;

public class GuiElementProxy extends BaseGuiElementProxy implements GuiElement{
	private GuiElementType elementType = GuiElementType.GENERIC;
	private ConcreteGuiElement<?,?> concreteElement = null;

	public GuiElementProxy(Page page, GuiElementMetaData emd) {
		super(page, emd);
	}
	
	public GuiElementProxy(GuiElementMetaData emd) {
		super(emd);
	}

	public GuiElement identify() throws Exception {
		try {
			this.getFinder().identify();
			return this;
		} catch (Exception e){
			return (GuiElement) throwElementIdentificationException(e, "identify", "identify element");
		}
	}

	private GuiElementType getType() {
		return this.elementType;
	}
	
	@Override
	public ConcreteGuiElement<?,?> getConcreteElement() {
		return this.concreteElement;
	}

	public void setConcreteElement(ConcreteGuiElement<?,?> element){
		this.concreteElement = element;
		this.setConcreteFinder(element);
	}
	
	@Override
	public void enterText(String text) throws Exception{
		try {
			getConcreteElement().enterText(text);} catch (Exception e){
			throwElementActionException(e, "enterText", "enter text in");
		}
	}


	@Override
	public boolean isPresent() throws Exception{
		try {
			return getConcreteElement().isPresent();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isPresent", "presence of");
		}
	}
	
	@Override
	public boolean isAbsent() throws Exception {
		try {
			return getConcreteElement().isAbsent();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isAbsent", "absence of");
		}
	}
	
	@Override
	public boolean isVisible() throws Exception {
		try {
			return getConcreteElement().isVisible();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isVisible", "visibility of");
		}
	}

	@Override
	public boolean isInvisible() throws Exception {
		try {
			return getConcreteElement().isInvisible();} catch (Exception e){
			return (boolean) throwElementInquiryException(e, "isInvisible", "invisibility of");
		}
	}


	@Override
	public void click() throws Exception {
		try {
			getConcreteElement().click();} catch (Exception e){
			throwElementActionException(e, "click", "click");
		}
	}

	@Override
	public void focus() throws Exception {
		try {
			getConcreteElement().focus();} catch (Exception e){
			throwElementActionException(e, "focus", "focus on");
		}
	}

	@Override
	public void waitForPresence() throws Exception {
		try {
			getConcreteElement().waitForPresence();} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ELEMENT_WAIT_FAILURE,
					"waitForPresence",
					String.format(
							Daksha.problem.ELEMENT_WAIT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							this.getConcreteElement().getWaitTime(),
							"presence of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}
	

	@Override
	public void waitForAbsence() throws Exception {
		try {
			getConcreteElement().waitForAbsence();} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ELEMENT_WAIT_FAILURE,
					"waitForAbsence",
					String.format(
							Daksha.problem.ELEMENT_WAIT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							this.getConcreteElement().getWaitTime(),
							"absence of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}

	@Override
	public void waitForVisibility() throws Exception {
		try {
			getConcreteElement().waitForVisibility();} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ELEMENT_WAIT_FAILURE,
					"waitForVisibility",
					String.format(
							Daksha.problem.ELEMENT_WAIT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							this.getConcreteElement().getWaitTime(),
							"visibility of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}

	@Override
	public void waitForInvisibility() throws Exception {
		try {
			getConcreteElement().waitForInvisibility();} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ELEMENT_WAIT_FAILURE,
					"waitForInvisibility",
					String.format(
							Daksha.problem.ELEMENT_WAIT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							this.getConcreteElement().getWaitTime(),
							"invisibility of",
							getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString()
							)
					);
		}
	}

	@Override
	public void setText(String text) throws Exception {
		try {
			getConcreteElement().setText(text);} catch (Exception e){
			throwElementActionException(e, "setText", "set text of");
		}
	}

	public void clearText() throws Exception {
		try {
			getConcreteElement().clearText();} catch (Exception e){
			throwElementActionException(e, "clearText", "clear text of");
		}
	}

	public void check() throws Exception {
		try {
			getConcreteElement().check();} catch (Exception e){
			throwElementActionException(e, "check", "check checkbox");
		}
	}

	public void uncheck() throws Exception {
		try {
			getConcreteElement().uncheck();} catch (Exception e){
			throwElementActionException(e, "uncheck", "uncheck checkbox");
		}
	}

	public void toggle() throws Exception {
		try {
			getConcreteElement().toggle();} catch (Exception e){
			throwElementActionException(e, "toggle", "toggle checkbox");
		}
	}

	/*
	 * Selection API
	 */

	public void selectText(String text) throws Exception {
		try {
			getConcreteElement().selectText(text);} catch (Exception e){
			throwElementActionException(
					e, "selectLabel",
					String.format("select label %s from %s", text, this.getType().toString().toLowerCase())
					);
		}
	}	

	public void select(String text) throws Exception {
		try {
			getConcreteElement().select(text);} catch (Exception e){
			throwElementActionException(
					e, "select",
					String.format("select label %s from %s", text, this.getType().toString().toLowerCase())
					);
		}
	}

	public void selectValue(String value) throws Exception {
		try {
			getConcreteElement().selectValue(value);} catch (Exception e){
			throwElementActionException(
					e, "selectValue",
					String.format("select value %s from %s", value, this.getType().toString().toLowerCase())
					);
		}
	}

	public void selectAtIndex(int index) throws Exception {
		try {
			getConcreteElement().selectAtIndex(index);} catch (Exception e){
			throwElementActionException(
					e, "selectIndex",
					String.format("select option at index %d from %s", index, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedText(String text) throws Exception {
		try {
			return getConcreteElement().hasSelectedText(text);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedLabel",
					String.format("whether label %s is selected for %s", text, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedValue(String value) throws Exception {
		try {
			return getConcreteElement().hasSelectedValue(value);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedValue",
					String.format("whether value %s is selected for %s", value, this.getType().toString().toLowerCase())
					);
		}
	}

	public boolean hasSelectedIndex(int index) throws Exception {
		try {
			return getConcreteElement().hasSelectedIndex(index);} catch (Exception e){
			return (boolean) throwElementInquiryException(
					e, "hasSelectedIndex",
					String.format("whether option at index %d is selected for %s", index, this.getType().toString().toLowerCase())
					);
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllOptions() throws Exception {
		try {
			return getConcreteElement().getAllOptions();} catch (Exception e){
			return (List<String>) throwElementGetAttributeException(
					e, "getAllLabels",
					String.format("all labels for %s", this.getType().toString().toLowerCase())
					);
		}
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllValues() throws Exception {
		try {
			return getConcreteElement().getAllValues();} catch (Exception e){
			return (List<String>) throwElementGetAttributeException(
					e, "getAllValues",
					String.format("all values for %s", this.getType().toString().toLowerCase())
					);
		}
	}


	public int getOptionCount() throws Exception {
		try {
			return getConcreteElement().getOptionCount();} catch (Exception e){
			return (int) throwElementGetAttributeException(
					e, "getOptionCount",
					String.format("option count for %s", this.getType().toString().toLowerCase())
					);
		}
	}

	// Properties


	public String getText() throws Exception {
		try {
			return getConcreteElement().getText();} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getText","text of");
		}
	}


	public String getValue() throws Exception {
		try {
			return getConcreteElement().getValue();} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getValue","value of");
		}
	}


	public String getAttribute(String attr) throws Exception {
		try {
			return getConcreteElement().getAttribute(attr);} catch (Exception e){
			return (String) throwElementGetAttributeException(e, "getAttribute",String.format("value of %s attribute of", attr));
		}
	}

	// Frame related action

	public void switchToFrame() throws Exception {
		this.getConcreteElement().switchToFrame();
	}

	public void hover() throws Exception {
		try{
			this.getConcreteElement().hover();} catch (Exception e){
			throwElementActionException(e, "hover", "hover on");
		}
	}


	public void hoverAndClick() throws Exception {
		try{
			this.getConcreteElement().hoverAndClick();} catch (Exception e){
			throwElementActionException(e, "hoverAndClick", "hover and click on");
		}
	}


	public void rightClick() throws Exception {
		try{
			this.getConcreteElement().rightClick();} catch (Throwable e){
			throwElementActionException(e, "rightClick", "right click on");
		}
	}

	@Override
	public Object throwUnsupportedActionException(String action) throws Exception {
		return throwUnsupportedException(action);		
	}

	@Override
	public Object throwUnsupportedSelectActionException(String action) throws Exception {
		return throwUnsupportedActionExceptionFromProxy(Daksha.problem.ELEMENT_UNSUPPORTED_SELECT_ACTION, action);
	}

	@Override
	public void hoverAndClickElement(String name) throws Exception {
		try{
			this.getConcreteElement().hoverAndClickElement(name);} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ACTION_MULTIELEMENT_FAILURE,
					"hoverAndClickElement",
					String.format(
							Daksha.problem.ACTION_MULTIELEMENT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							"hover on",
							this.getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString(),
							"click on",
							name,
							""
							)
					);
		}
	}

	@Override
	public void rightClickAndClickElement(String name) throws Exception {
		try{
			this.getConcreteElement().rightClickAndClickElement(name);} catch (Exception e){
			throwElementException(
					e,
					Daksha.problem.ACTION_MULTIELEMENT_FAILURE,
					"rightClickAndClicElement",
					String.format(
							Daksha.problem.ACTION_MULTIELEMENT_FAILURE,
							this.getConcreteElement().getAutomatorName(),
							"right click on",
							this.getElementNameFillerForException(),
							this.getMetaData().getAllProperties().toString(),
							"click on",
							name,
							""	
							)
					);
		}
	}

	@Override
	public void waitUntilPresent() throws Exception {
		getConcreteElement().waitUntilPresent();
	}

	@Override
	public void waitUntilAbsent() throws Exception {
		getConcreteElement().waitUntilAbsent();
	}

	@Override
	public void waitUntilClickable() throws Exception {
		getConcreteElement().waitUntilClickable();
	}

	@Override
	public void waitUntilVisible() throws Exception {
		getConcreteElement().waitUntilVisible();
	}

	@Override
	public void waitUntilInvisible() throws Exception {
		getConcreteElement().waitUntilInvisible();
	}

	@Override
	public boolean isClickable() throws Exception {
		return getConcreteElement().isClickable();
	}
	
	@Override
	public void doubleClick() throws Exception {
		getConcreteElement().doubleClick();
	}

	@Override
	public void dragAndDrop(String name) throws Exception {
		getConcreteElement().dragAndDrop(name);
	}

}
